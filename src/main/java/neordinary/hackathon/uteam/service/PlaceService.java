package neordinary.hackathon.uteam.service;

import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.constant.ConstantUtils;
import neordinary.hackathon.uteam.domain.Address;
import neordinary.hackathon.uteam.domain.Place;
import neordinary.hackathon.uteam.domain.Position;
import neordinary.hackathon.uteam.dto.place.PlaceDto;
import neordinary.hackathon.uteam.dto.place.request.PlaceRequest;
import neordinary.hackathon.uteam.exception.place.PlaceImageFindingException;
import neordinary.hackathon.uteam.exception.place.PlaceNotFoundByIdException;
import neordinary.hackathon.uteam.repository.PlaceRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Transactional
    public Place save(PlaceRequest placeReq) {
        String placeImageUrl = findPlaceImageUrl(placeReq.getKakaoPid());
        return placeRepository.save(Place.of(
                placeReq.getKakaoPid(),
                placeReq.getName(),
                placeReq.getCategory(),
                placeImageUrl,
                new Address(placeReq.getLotNumberAddress(), placeReq.getRoadAddress()),
                new Position(placeReq.getLat(), placeReq.getLng()),
                placeReq.getUrl()
        ));
    }

    public String findPlaceImageUrl(String kakaoPid) {
        String url = "https://place.map.kakao.com/placePrint.daum?confirmid=" + kakaoPid;
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException ex) {
            throw new PlaceImageFindingException();
        }

        Element element = doc.selectFirst(".popup_body .wrap_info .thumb_info > img");
        return element != null ? element.absUrl("src") : ConstantUtils.PLACE_DEFAULT_IMAGE_URL;
    }
}
