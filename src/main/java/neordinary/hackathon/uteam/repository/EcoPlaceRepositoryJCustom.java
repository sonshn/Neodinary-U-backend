package neordinary.hackathon.uteam.repository;

import neordinary.hackathon.uteam.domain.EcoPlace;
import neordinary.hackathon.uteam.domain.Position;

public interface EcoPlaceRepositoryJCustom {

    EcoPlace findNearestEcoPlace(Position pos);

    EcoPlace findNearestEcoPlace(Position pos1, Position pos2);
}
