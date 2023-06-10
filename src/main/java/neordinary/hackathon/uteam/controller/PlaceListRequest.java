package neordinary.hackathon.uteam.controller;

import lombok.Getter;
import neordinary.hackathon.uteam.dto.place.request.PlaceRequest;

import java.util.List;

@Getter
public class PlaceListRequest {

    List<PlaceRequest> places;
}
