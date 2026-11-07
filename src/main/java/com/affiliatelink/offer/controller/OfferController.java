package com.affiliatelink.offer.controller;
import com.affiliatelink.offer.dto.*;
import com.affiliatelink.offer.model.Niche;
import com.affiliatelink.offer.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/offres")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping
    public ResponseEntity<CreateOfferRes> create(@Valid @RequestBody CreateOfferReq req) {
        CreateOfferRes res = offerService.createOffer(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindOfferRes> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(offerService.findbyId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FindOfferRes> update(@PathVariable("id") Long id,
                                               @Valid @RequestBody UpdateOfferReq req) {
        return ResponseEntity.status(HttpStatus.OK).body(offerService.update(id, req));
    }

    @GetMapping
    public ResponseEntity<PagedModel<SearchOfferRes>> search(
            @RequestParam(name = "niche", required = false) Niche niche,
            @RequestParam(name = "advertiserId", required = false) Long advertiserId,
            @RequestParam(name = "tag", required = false) String tag,
            @RequestParam(name = "keyword", required = false) String keyword,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<SearchOfferRes> page = offerService.search(niche, advertiserId, tag, keyword, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new PagedModel<>(page));
    }
}