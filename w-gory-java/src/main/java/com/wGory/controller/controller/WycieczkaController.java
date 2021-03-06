package com.wGory.controller.controller;

import com.wGory.controller.service.WycieczkaService;
import com.wGory.model.Wycieczka;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/wycieczki")
@RestController
@CrossOrigin
public class WycieczkaController {
    private WycieczkaService wycieczkaService;

    public WycieczkaController(WycieczkaService wycieczkaService) {
        this.wycieczkaService = wycieczkaService;
    }


    @ApiOperation(value = "Uzyskanie istniejącej wycieczki w bazie danych po jej numerze id.")
    @ApiImplicitParam(
            name = "id",
            value = "Unikatowy numer identyfikujący wycieczkę w bazie danych.",
            required = true,
            dataType = "Integer",
            example = "1")
    @GetMapping("/{id}")
    public ResponseEntity<Wycieczka> getWycieczkaById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(wycieczkaService.getWycieczkaById(id));
    }

    @ApiOperation(value = "Status wycieczki zostaje zmieniony na \"Odbyta\", a zmiana zapisana w bazie danych.")
    @ApiImplicitParam(
            name = "id",
            value = "Unikatowy numer identyfikujący wycieczkę w bazie danych.",
            required = true,
            dataType = "Long",
            example = "1")
    @PutMapping("/done/{id}")
    public ResponseEntity<Wycieczka> setWycieczkaDone(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(wycieczkaService.setWycieczkaDone(id));
    }
}
