package io.zipcoder.tc_spring_poll_application.controller;

import io.zipcoder.tc_spring_poll_application.domain.Vote;
import io.zipcoder.tc_spring_poll_application.dtos.OptionCount;
import io.zipcoder.tc_spring_poll_application.dtos.VoteResult;
import io.zipcoder.tc_spring_poll_application.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ComputeResultController {

    private VoteRepository voteRepository;

    @Autowired
    public ComputeResultController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findVotesByPoll(pollId);

        ArrayList<OptionCount> c = new ArrayList<>();
        boolean flag = true;
        voteResult.setResults(c);

        for (Vote v:allVotes) {
            if(flag) {
                OptionCount newOc = new OptionCount();
                newOc.setOptionId(v.getOption().getId());
                newOc.setCount(1);
                c.add(newOc);
                flag = false;
            }
            for (int i = 0; i<c.size(); i++) {
                if(v.getOption().getId().equals(c.get(i).getOptionId())){
                    c.get(i).setCount( c.get(i).getCount()+1 );
                }else{
                    OptionCount newOc = new OptionCount();
                    newOc.setOptionId(v.getOption().getId());
                    newOc.setCount(1);
                    c.add(newOc);
                }
            }

        }
        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }

}
