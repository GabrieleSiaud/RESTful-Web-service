package lt.viko.eif.gsiaudvytyte.TreciasDarbas1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lt.viko.eif.gsiaudvytyte.TreciasDarbas1.db.BandRepository;
import lt.viko.eif.gsiaudvytyte.TreciasDarbas1.modal.Band;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


/**
 * Controller for handling HTTP requests related to {@link Band} entities.
 * Supports retrieving all bands, retrieving a single band by ID,
 * creating new bands, and deleting bands.
 */

@Tag(name = "Bands", description = "Operations related to Band entities")
@RestController
@RequestMapping("/bands")
public class BandController {
    private final BandRepository repository;
    public BandController(BandRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Get all bands", description = "Returns a list of all bands with HATEOAS links")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of bands")
    })
    @Cacheable
    @GetMapping
    public CollectionModel<EntityModel<Band>> all() {
        List<EntityModel<Band>> bands = repository.findAll().stream()
                .map(band -> EntityModel.of(band,
                        linkTo(methodOn(BandController.class).one((long) band.getId())).withSelfRel(),
                        linkTo(methodOn(MemberController.class).allByBand((long) band.getId())).withRel("members")))
                .collect(Collectors.toList());
        return CollectionModel.of(bands, linkTo(methodOn(BandController.class).all()).withSelfRel());
    }

    @Operation(summary = "Get a band by ID", description = "Returns a single band by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Band found"),
            @ApiResponse(responseCode = "404", description = "Band not found")
    })
    @Cacheable
    @GetMapping("/{bandId}")
    public EntityModel<Band> one(@PathVariable Long bandId) {
        Band band = repository.findById(bandId)
                .orElseThrow(() -> new BandNotFoundExeption(bandId));

        return EntityModel.of(band,
                linkTo(methodOn(BandController.class).one(bandId)).withSelfRel(),
                linkTo(methodOn(BandController.class).all()).withRel("bands"));

    }

    @Operation(summary = "Delete a band", description = "Deletes the band with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Band deleted"),
            @ApiResponse(responseCode = "400", description = "Band not found")
    })
    @DeleteMapping("/{bandId}")
    public void deleteBand(@PathVariable Long bandId) {
        Band band = repository.findById(bandId)
                .orElseThrow(() -> new BandNotFoundExeption(bandId));
        repository.delete(band);
    }

    @Operation(summary = "Create a new band", description = "Adds a new band to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Band created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public Band createBand(@RequestBody Band newBand) {
        return repository.save(newBand);
    }
}
