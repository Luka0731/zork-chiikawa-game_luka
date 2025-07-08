package ch.noseryoung.blj;

import ch.noseryoung.blj.engine.Place;
import ch.noseryoung.blj.loaders.PlaceLoader;
import ch.noseryoung.blj.util.frontendSamples.Output;

import java.util.ArrayList;
import java.util.List;

public class GameScene extends Scene {
    private static GameScene instance;
    private final List<Place> places;
    private Place currentPlace;


    // |--- singleton ---|

    private GameScene() {
        PlaceLoader placeLoader = new PlaceLoader();
        places = placeLoader.load("places");

        currentPlace = places.getFirst();
    }

    public static GameScene getInstance() {
        if (GameScene.instance == null) {
            GameScene.instance = new GameScene();
        }
        return GameScene.instance;
    }


    // |--- game loop ---|

    public void update() {
        isRunning = true;
        while (isRunning) {
            currentPlace.update();
        }
    }


    // |--- change the current place  ---|

        public void changePlaceById(String placeId) {
            try {
                for (Place place : places) {
                    if (place.getId().equals(placeId)) {
                        currentPlace = place;
                        break;
                    }
                }
            } catch (IllegalArgumentException e) {
                Output.printlnError("Place with ID not found", placeId);
            }
    }



    // |--- getters & setter ---|

}
