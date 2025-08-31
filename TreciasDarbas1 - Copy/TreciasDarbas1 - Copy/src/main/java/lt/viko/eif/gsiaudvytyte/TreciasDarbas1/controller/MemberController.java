package lt.viko.eif.gsiaudvytyte.TreciasDarbas1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lt.viko.eif.gsiaudvytyte.TreciasDarbas1.db.BandRepository;
import lt.viko.eif.gsiaudvytyte.TreciasDarbas1.db.MemberRepository;
import lt.viko.eif.gsiaudvytyte.TreciasDarbas1.modal.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing {@link Member} entities within a specific {@link lt.viko.eif.gsiaudvytyte.TreciasDarbas1.modal.Band}.
 * Provides endpoints to list members of a band and to create new members for a band.
 */
@Tag(name = "Members", description = "Operations related to Members within Bands")
@RestController
@RequestMapping("/bands")
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BandRepository bandRepository;

    @Operation(summary = "Get all members of a band", description = "Returns a list of all members belonging to the specified band")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of members"),
            @ApiResponse(responseCode = "404", description = "Band not found")
    })
    @GetMapping("/{bandId}/members")
    public List<Member> allByBand(@PathVariable Long bandId) {
        return memberRepository.findByBandId(bandId);
    }

    @Operation(summary = "Create a member for a band", description = "Creates a new member associated with the specified band")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Member created successfully"),
            @ApiResponse(responseCode = "404", description = "Band not found")
    })
    @PostMapping("/{bandId}/members")
    public ResponseEntity<Member> createMemberForBand(@PathVariable Long bandId,
                                                            @RequestBody Member memberRequest) {
        return bandRepository.findById(bandId)
                .map(band -> {
                    memberRequest.setBand(band);
                    Member savedMember = memberRepository.save(memberRequest);
                    return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}
