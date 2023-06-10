package neordinary.hackathon.uteam.service;

import lombok.RequiredArgsConstructor;
import neordinary.hackathon.uteam.constant.ConstantUtils;
import neordinary.hackathon.uteam.domain.Address;
import neordinary.hackathon.uteam.domain.Place;
import neordinary.hackathon.uteam.domain.Position;
import neordinary.hackathon.uteam.dto.place.request.PlaceRequest;
import neordinary.hackathon.uteam.dto.place.response.PlaceResponseForRecommendedCourse;
import neordinary.hackathon.uteam.exception.place.PlaceImageFindingException;
import neordinary.hackathon.uteam.exception.place.PlaceListEmptyException;
import neordinary.hackathon.uteam.repository.EcoPlaceRepository;
import neordinary.hackathon.uteam.repository.PlaceRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final EcoPlaceRepository ecoPlaceRepository;

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

    public List<PlaceResponseForRecommendedCourse> getIncludeRecommendedPlaces(List<PlaceRequest> placeReqList) {
        List<PlaceResponseForRecommendedCourse> result = new ArrayList<>();

        if (placeReqList.size() == 0) {
            throw new PlaceListEmptyException();
        }

        result.add(PlaceResponseForRecommendedCourse.from(placeReqList.get(0)));

        if (placeReqList.size() == 1) {
            result.add(PlaceResponseForRecommendedCourse.from(
                    ecoPlaceRepository.findNearestEcoPlace(
                            new Position(placeReqList.get(0).getLat(), placeReqList.get(0).getLng())
                    )
            ));
            return result;
        }

        for (int i = 1; i < placeReqList.size(); i++) {
            result.add(PlaceResponseForRecommendedCourse.from(
                    ecoPlaceRepository.findNearestEcoPlace(
                            new Position(placeReqList.get(i - 1).getLat(), placeReqList.get(i - 1).getLng()),
                            new Position(placeReqList.get(i).getLat(), placeReqList.get(i).getLng())
                    )
            ));
            result.add(PlaceResponseForRecommendedCourse.from(placeReqList.get(i)));
        }
        return result;
    }
}
