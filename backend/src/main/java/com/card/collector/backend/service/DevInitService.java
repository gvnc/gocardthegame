package com.card.collector.backend.service;


import com.card.collector.backend.model.*;
import com.card.collector.backend.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DevInitService {

    Logger logger = LoggerFactory.getLogger(DevInitService.class);

    @Autowired
    UserService userService;

    @Autowired
    CollectionRepository collectionRepository;

    @Autowired
    CollectionCategoryRepository collectionCategoryRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountCardRepository accountCardRepository;

    @Autowired
    AccountCollectionRepository accountCollectionRepository;

    @Autowired
    AchievementRepository achievementRepository;

    @Autowired
    ProfileService profileService;

    @Autowired
    private CardService cardService;

    @Autowired
    private CollectionService collectionService;

    public void init() {

        logger.debug("Started to insert data");

        createAchievements();

        createAnimalsCategory();
        createEpicCategory();
        createLifeCategory();
        createChildCategory();
        createSportsCategory();

        /*
        userService.signup("Q","Q", "Q", SignInType.REGULAR.toString());
        userService.signup("Q","q@q.com", "1", SignInType.REGULAR.toString());
        userService.signup("ali", "ali@ali.com", "ali", SignInType.REGULAR.toString());

        User user = userService.findByEmail("Q");

        Account account = accountRepository.findById(user.getAccountId()).get();
        GameStats gameStats = account.getGameStats();
        gameStats.setTotalCardsOpened(144);
        account.setGameStats(gameStats);
        accountRepository.save(account);

       // profileService.accomplishAchievementById(1,1);
       // profileService.accomplishAchievementById(1,2);

        Collection seaLifeCollection = collectionRepository.getByTitle("data.animals.seaLife.title");

        collectionService.addCollectionToAccount(account.getId(), seaLifeCollection.getId());
        collectionService.addCollectionToAccount(account.getId(), 3);
        collectionService.addCollectionToAccount(account.getId(), 5);

        cardService.openRandomCard(account.getId(), seaLifeCollection.getId());
        cardService.openRandomCard(account.getId(), seaLifeCollection.getId());
        cardService.openCardOfSpecificClass(account.getId(), seaLifeCollection.getId(), CardClass.Rare.toString());
        cardService.openCardOfSpecificClass(account.getId(), seaLifeCollection.getId(), CardClass.Unique.toString());
*/
        logger.debug("Completed data insert");
    }

    private void createAnimalsCategory() {
        CollectionCategory collectionCategory = new CollectionCategory();
        collectionCategory.setTitle("data.animals.title");
        collectionCategoryRepository.save(collectionCategory);

        createSeaLifeCollection(collectionCategory);
        createSafariCollection(collectionCategory);
        createPoleLifeCollection(collectionCategory);
        createForestCollection(collectionCategory);
    }

    private void createEpicCategory() {
        CollectionCategory collectionCategory = new CollectionCategory();
        collectionCategory.setTitle("data.epic.title");
        collectionCategoryRepository.save(collectionCategory);

        createZodiacCollection(collectionCategory);
        createGreeksCollection(collectionCategory);
        createHalloweenCollection(collectionCategory);
        createEpicMoviesCollection(collectionCategory);
    }

    private void createChildCategory() {
        CollectionCategory collectionCategory = new CollectionCategory();
        collectionCategory.setTitle("data.child.title");
        collectionCategoryRepository.save(collectionCategory);

        createToysBoysCollection(collectionCategory);
        createPlushToys(collectionCategory);
        createStories(collectionCategory);
        createCircus(collectionCategory);
    }

    private void createLifeCategory(){
        CollectionCategory collectionCategory = new CollectionCategory();
        collectionCategory.setTitle("data.life.title");
        collectionCategoryRepository.save(collectionCategory);

        createMoneyCollection(collectionCategory);
        createWondersCollection(collectionCategory);
        createAmazingCarsCollection(collectionCategory);
        createSandSculpture(collectionCategory);
    }

    private void createSportsCategory(){
        CollectionCategory collectionCategory = new CollectionCategory();
        collectionCategory.setTitle("data.sports.title");
        collectionCategoryRepository.save(collectionCategory);

        createWorldCupCollection(collectionCategory);
        createRodeoCollection(collectionCategory);
        createMonsterTruckCollection(collectionCategory);
        createFootballersCollection(collectionCategory);
    }

    private void createSeaLifeCollection(CollectionCategory collectionCategory){

        String dataPath = "data.animals.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "seaLife.title");
        collection.setCapImageUrl("seaLife/turtle");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "seaLife.clownFish.title", dataPath + "seaLife.clownFish.description", "seaLife/clownfish", 1056, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "seaLife.jellyFish.title", dataPath + "seaLife.jellyFish.description", "seaLife/jellyfish", 2164, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "seaLife.pufferFish.title", dataPath + "seaLife.pufferFish.description", "seaLife/pufferfish", 3108, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "seaLife.crab.title", dataPath + "seaLife.crab.description", "seaLife/crab", 1099, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "seaLife.coralReef.title", dataPath + "seaLife.coralReef.description", "seaLife/coralreef", 1085, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "seaLife.starFish.title", dataPath + "seaLife.starFish.description", "seaLife/starfish", 1240, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "seaLife.seaHorse.title", dataPath + "seaLife.seaHorse.description", "seaLife/seahorse", 2026, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "seaLife.octopus.title", dataPath + "seaLife.octopus.description", "seaLife/octopus", 1190, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "seaLife.turtle.title", dataPath + "seaLife.turtle.description", "seaLife/turtle", 1360, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "seaLife.ray.title", dataPath + "seaLife.ray.description", "seaLife/ray", 1407, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "seaLife.swordFish.title", dataPath + "seaLife.swordFish.description", "seaLife/swordfish", 1208, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "seaLife.dolphin.title", dataPath + "seaLife.dolphin.description", "seaLife/dolphin", 1087, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "seaLife.orca.title", dataPath + "seaLife.orca.description", "seaLife/orca", 3350, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "seaLife.shark.title", dataPath + "seaLife.shark.description", "seaLife/shark", 1216, collectionId, CardClass.Common));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createSafariCollection(CollectionCategory collectionCategory){

        String dataPath = "data.animals.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "safari.title");
        collection.setCapImageUrl("safari/hyena");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "safari.antelope.title", dataPath + "safari.antelope.description", "safari/antelope", 1087, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "safari.buffalo.title", dataPath + "safari.buffalo.description", "safari/buffalo", 1170, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "safari.cheetah.title", dataPath + "safari.cheetah.description", "safari/cheetah", 3356, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "safari.crocodile.title", dataPath + "safari.crocodile.description", "safari/crocodile", 2187, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "safari.elephant.title", dataPath + "safari.elephant.description", "safari/elephant", 3252, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "safari.giraffe.title", dataPath + "safari.giraffe.description", "safari/giraffe", 2300, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "safari.hippo.title", dataPath + "safari.hippo.description", "safari/hippo", 1036, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "safari.hyena.title", dataPath + "safari.hyena.description", "safari/hyena", 1142, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "safari.lion.title", dataPath + "safari.lion.description", "safari/lion", 1123, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "safari.oxheaded.title", dataPath + "safari.oxheaded.description", "safari/oxheadedantelope", 1290, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "safari.rhino.title", dataPath + "safari.rhino.description", "safari/rhino", 1305, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "safari.vulture.title", dataPath + "safari.vulture.description", "safari/vulture", 2273, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "safari.zebra.title", dataPath + "safari.zebra.description", "safari/zebra", 1091, collectionId, CardClass.Common));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createPoleLifeCollection(CollectionCategory collectionCategory){

        String dataPath = "data.animals.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "poles.title");
        collection.setCapImageUrl("poleLife/seal");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "poles.bear.title", dataPath + "poles.bear.description", "poleLife/bear", 3197, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "poles.reindeer.title", dataPath + "poles.reindeer.description", "poleLife/reindeer", 1305, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "poles.seal.title", dataPath + "poles.seal.description", "poleLife/seal", 1079, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "poles.gull.title", dataPath + "poles.gull.description", "poleLife/gull", 1230, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "poles.walrus.title", dataPath + "poles.walrus.description", "poleLife/walrus", 3051, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "poles.wolf.title", dataPath + "poles.wolf.description", "poleLife/wolf", 1125, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "poles.narwhall.title", dataPath + "poles.narwhall.description", "poleLife/narwhal", 2083, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "poles.belugaWhale.title", dataPath + "poles.belugaWhale.description", "poleLife/belugawhale", 1187, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "poles.fox.title", dataPath + "poles.fox.description", "poleLife/fox", 1074, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "poles.snowyOwl.title", dataPath + "poles.snowyOwl.description", "poleLife/snowyowl", 1135, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "poles.penguin.title", dataPath + "poles.penguin.description", "poleLife/penguin", 2481, collectionId, CardClass.Rare));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createForestCollection(CollectionCategory collectionCategory){

        String dataPath = "data.animals.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "forest.title");
        collection.setCapImageUrl("forest/chimpanzee");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "forest.bear.title", dataPath + "forest.bear.description", "forest/bear", 1309, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "forest.squirrel.title", dataPath + "forest.squirrel.description", "forest/squirrel", 1157, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "forest.fox.title", dataPath + "forest.fox.description", "forest/fox", 1231, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "forest.chimpanzee.title", dataPath + "forest.chimpanzee.description", "forest/chimpanzee", 1142, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "forest.wolf.title", dataPath + "forest.wolf.description", "forest/wolf", 1034, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "forest.blackPanther.title", dataPath + "forest.blackPanther.description", "forest/blackpanther", 1305, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "forest.jaguar.title", dataPath + "forest.jaguar.description", "forest/jaguar", 3266, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "forest.tiger.title", dataPath + "forest.tiger.description", "forest/tiger", 1275, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "forest.skunk.title", dataPath + "forest.skunk.description", "forest/skunk", 1264, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "forest.owl.title", dataPath + "forest.owl.description", "forest/owl", 1036, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "forest.bulletAnt.title", dataPath + "forest.bulletAnt.description", "forest/bulletant", 1064, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "forest.chameleon.title", dataPath + "forest.chameleon.description", "forest/chameleon", 2301, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "forest.macaw.title", dataPath + "forest.macaw.description", "forest/macaw", 1155, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "forest.toucan.title", dataPath + "forest.toucan.description", "forest/toucan", 1179, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "forest.frog.title", dataPath + "forest.frog.description", "forest/frog", 3409, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "forest.snake.title", dataPath + "forest.snake.description", "forest/snake", 2407, collectionId, CardClass.Rare));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createToysBoysCollection(CollectionCategory collectionCategory){

        String dataPath = "data.child.";
        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "toysForBoys.title");
        collection.setCapImageUrl("toysBoys/constructiontruck");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "toysForBoys.ambulance.title", dataPath + "toysForBoys.ambulance.description", "toysBoys/ambulance", 1128, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "toysForBoys.policeCar.title", dataPath + "toysForBoys.policeCar.description", "toysBoys/policecar", 2056, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "toysForBoys.fireTruck.title", dataPath + "toysForBoys.fireTruck.description", "toysBoys/firetruck", 1188, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "toysForBoys.garbageTruck.title", dataPath + "toysForBoys.garbageTruck.description", "toysBoys/garbagetruck", 1037, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "toysForBoys.bmw.title", dataPath + "toysForBoys.bmw.description", "toysBoys/bmw", 1370, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "toysForBoys.lamborghini.title", dataPath + "toysForBoys.lamborghini.description", "toysBoys/lamborghini", 3456, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "toysForBoys.delorean.title", dataPath + "toysForBoys.delorean.description", "toysBoys/delorean", 1219, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "toysForBoys.knightRider.title", dataPath + "toysForBoys.knightRider.description", "toysBoys/knightrider", 1137, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "toysForBoys.monster.title", dataPath + "toysForBoys.monster.description", "toysBoys/monster", 2021, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "toysForBoys.f1.title", dataPath + "toysForBoys.f1.description", "toysBoys/f1car", 1203, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "toysForBoys.truck.title", dataPath + "toysForBoys.truck.description", "toysBoys/truck", 1240, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "toysForBoys.helicopter.title", dataPath + "toysForBoys.helicopter.description", "toysBoys/helicopter", 1357, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "toysForBoys.plane.title", dataPath + "toysForBoys.plane.description", "toysBoys/plane", 1198, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "toysForBoys.forklift.title", dataPath + "toysForBoys.forklift.description", "toysBoys/forklift", 1190, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "toysForBoys.constTruck.title", dataPath + "toysForBoys.constTruck.description", "toysBoys/constructiontruck", 1264, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "toysForBoys.digger.title", dataPath + "toysForBoys.digger.description", "toysBoys/digger", 3237, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "toysForBoys.dumpTruck.title", dataPath + "toysForBoys.dumpTruck.description", "toysBoys/dumptruck", 1271, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "toysForBoys.mixer.title", dataPath + "toysForBoys.mixer.description", "toysBoys/mixer", 1169, collectionId, CardClass.Common));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createZodiacCollection(CollectionCategory collectionCategory){

        String dataPath = "data.epic.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "zodiac.title");
        collection.setCapImageUrl("zodiac/libra");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "zodiac.aries.title", dataPath + "zodiac.aries.description", "zodiac/aries", 1319, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "zodiac.taurus.title", dataPath + "zodiac.taurus.description", "zodiac/taurus", 3157, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "zodiac.gemini.title", dataPath + "zodiac.gemini.description", "zodiac/gemini", 2231, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "zodiac.cancer.title", dataPath + "zodiac.cancer.description", "zodiac/cancer", 1242, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "zodiac.leo.title", dataPath + "zodiac.leo.description", "zodiac/leo", 1231, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "zodiac.virgo.title", dataPath + "zodiac.virgo.description", "zodiac/virgo", 2305, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "zodiac.libra.title", dataPath + "zodiac.libra.description", "zodiac/libra", 1266, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "zodiac.scorpio.title", dataPath + "zodiac.scorpio.description", "zodiac/scorpio", 3275, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "zodiac.sagittarius.title", dataPath + "zodiac.sagittarius.description", "zodiac/sagittarius", 1104, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "zodiac.capricorn.title", dataPath + "zodiac.capricorn.description", "zodiac/capricorn", 1238, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "zodiac.aquarius.title", dataPath + "zodiac.aquarius.description", "zodiac/aquarius", 1104, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "zodiac.pisces.title", dataPath + "zodiac.pisces.description", "zodiac/pisces", 1301, collectionId, CardClass.Common));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createGreeksCollection(CollectionCategory collectionCategory){

        String dataPath = "data.epic.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "greeks.title");
        collection.setCapImageUrl("greeks/ares");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "greeks.aphrodite.title", dataPath + "greeks.aphrodite.description", "greeks/aphrodite", 1029, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "greeks.apollo.title", dataPath + "greeks.apollo.description", "greeks/apollo", 1137, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "greeks.ares.title", dataPath + "greeks.ares.description", "greeks/ares", 1051, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "greeks.artemis.title", dataPath + "greeks.artemis.description", "greeks/artemis", 1103, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "greeks.athena.title", dataPath + "greeks.athena.description", "greeks/athena", 2097, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "greeks.demetra.title", dataPath + "greeks.demetra.description", "greeks/demetra", 1305, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "greeks.dionysus.title", dataPath + "greeks.dionysus.description", "greeks/dionysus", 1161, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "greeks.hades.title", dataPath + "greeks.hades.description", "greeks/hades", 1027, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "greeks.hephaestus.title", dataPath + "greeks.hephaestus.description", "greeks/hephaestus", 3204, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "greeks.hera.title", dataPath + "greeks.hera.description", "greeks/hera", 1023, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "greeks.hermes.title", dataPath + "greeks.hermes.description", "greeks/hermes", 1210, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "greeks.medusa.title", dataPath + "greeks.medusa.description", "greeks/medusa", 1157, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "greeks.nike.title", dataPath + "greeks.nike.description", "greeks/nike", 1278, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "greeks.poseidon.title", dataPath + "greeks.poseidon.description", "greeks/poseidon", 2214, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "greeks.zeus.title", dataPath + "greeks.zeus.description", "greeks/zeus", 3310, collectionId, CardClass.Unique));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createWorldCupCollection(CollectionCategory collectionCategory){

        String dataPath = "data.sports.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "worldCup.title");
        collection.setCapImageUrl("worldCup/2006");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "worldCup.1930.title", dataPath + "worldCup.1930.description", "worldCup/1930", 1218, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "worldCup.1934.title", dataPath + "worldCup.1934.description", "worldCup/1934", 1302, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "worldCup.1938.title", dataPath + "worldCup.1938.description", "worldCup/1938", 1275, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "worldCup.1950.title", dataPath + "worldCup.1950.description", "worldCup/1950", 1197, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "worldCup.1954.title", dataPath + "worldCup.1954.description", "worldCup/1954", 1138, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "worldCup.1958.title", dataPath + "worldCup.1958.description", "worldCup/1958", 2021, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "worldCup.1962.title", dataPath + "worldCup.1962.description", "worldCup/1962", 1152, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "worldCup.1966.title", dataPath + "worldCup.1966.description", "worldCup/1966", 1160, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "worldCup.1970.title", dataPath + "worldCup.1970.description", "worldCup/1970", 1083, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "worldCup.1974.title", dataPath + "worldCup.1974.description", "worldCup/1974", 3272, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "worldCup.1978.title", dataPath + "worldCup.1978.description", "worldCup/1978", 3191, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "worldCup.1982.title", dataPath + "worldCup.1982.description", "worldCup/1982", 1005, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "worldCup.1986.title", dataPath + "worldCup.1986.description", "worldCup/1986", 1216, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "worldCup.1990.title", dataPath + "worldCup.1990.description", "worldCup/1990", 1128, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "worldCup.1994.title", dataPath + "worldCup.1994.description", "worldCup/1994", 1342, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "worldCup.1998.title", dataPath + "worldCup.1998.description", "worldCup/1998", 1261, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "worldCup.2002.title", dataPath + "worldCup.2002.description", "worldCup/2002", 1179, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "worldCup.2006.title", dataPath + "worldCup.2006.description", "worldCup/2006", 1280, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "worldCup.2010.title", dataPath + "worldCup.2010.description", "worldCup/2010", 2292, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "worldCup.2014.title", dataPath + "worldCup.2014.description", "worldCup/2014", 1304, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "worldCup.2018.title", dataPath + "worldCup.2018.description", "worldCup/2018", 1315, collectionId, CardClass.Common));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }


    private void createRodeoCollection(CollectionCategory collectionCategory){

        String dataPath = "data.sports.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "rodeo.title");
        collection.setCapImageUrl("rodeo/david");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "rodeo.alistair.title", dataPath + "rodeo.alistair.description", "rodeo/alistair", 3012, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "rodeo.david.title", dataPath + "rodeo.david.description", "rodeo/david", 1145, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "rodeo.ethan.title", dataPath + "rodeo.ethan.description", "rodeo/ethan", 1103, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "rodeo.james.title", dataPath + "rodeo.james.description", "rodeo/james", 1056, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "rodeo.kevin.title", dataPath + "rodeo.kevin.description", "rodeo/kevin", 2357, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "rodeo.kylo.title", dataPath + "rodeo.kylo.description", "rodeo/kylo", 1234, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "rodeo.mason.title", dataPath + "rodeo.mason.description", "rodeo/mason", 1046, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "rodeo.michael.title", dataPath + "rodeo.michael.description", "rodeo/michael", 1170, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "rodeo.nathan.title", dataPath + "rodeo.nathan.description", "rodeo/nathan", 1160, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "rodeo.oscar.title", dataPath + "rodeo.oscar.description", "rodeo/oscar", 1092, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "rodeo.owen.title", dataPath + "rodeo.owen.description", "rodeo/owen", 1093, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "rodeo.richard.title", dataPath + "rodeo.richard.description", "rodeo/richard", 1194, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "rodeo.shepherd.title", dataPath + "rodeo.shepherd.description", "rodeo/shepherd", 2036, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "rodeo.thomas.title", dataPath + "rodeo.thomas.description", "rodeo/thomas", 3250, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "rodeo.walter.title", dataPath + "rodeo.walter.description", "rodeo/walter", 1140, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "rodeo.william.title", dataPath + "rodeo.william.description", "rodeo/william", 1037, collectionId, CardClass.Common));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createMonsterTruckCollection(CollectionCategory collectionCategory){

        String dataPath = "data.sports.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "monsterTrucks.title");
        collection.setCapImageUrl("monsterTrucks/mad-scientist");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "monsterTrucks.badnews.title", dataPath + "monsterTrucks.badnews.description", "monsterTrucks/badnews", 1097, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "monsterTrucks.brutus.title", dataPath + "monsterTrucks.brutus.description", "monsterTrucks/brutus", 1181, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "monsterTrucks.cleatus.title", dataPath + "monsterTrucks.cleatus.description", "monsterTrucks/cleatus", 1184, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "monsterTrucks.crushstation.title", dataPath + "monsterTrucks.crushstation.description", "monsterTrucks/crush-station", 1272, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "monsterTrucks.dalmatian.title", dataPath + "monsterTrucks.dalmatian.description", "monsterTrucks/dalmatian", 1177, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "monsterTrucks.dragon.title", dataPath + "monsterTrucks.dragon.description", "monsterTrucks/dragon", 2248, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "monsterTrucks.eldiablo.title", dataPath + "monsterTrucks.eldiablo.description", "monsterTrucks/eldiablo", 1369, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "monsterTrucks.eltoroloco.title", dataPath + "monsterTrucks.eltoroloco.description", "monsterTrucks/eltoroloco", 1124, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "monsterTrucks.gasmonkey.title", dataPath + "monsterTrucks.gasmonkey.description", "monsterTrucks/gas-monkey", 1013, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "monsterTrucks.hotwheels.title", dataPath + "monsterTrucks.hotwheels.description", "monsterTrucks/hot-wheels", 1325, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "monsterTrucks.madmax.title", dataPath + "monsterTrucks.madmax.description", "monsterTrucks/madmax", 3036, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "monsterTrucks.madscientist.title", dataPath + "monsterTrucks.madscientist.description", "monsterTrucks/mad-scientist", 1347, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "monsterTrucks.maxd.title", dataPath + "monsterTrucks.maxd.description", "monsterTrucks/max-d", 1188, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "monsterTrucks.megalodon.title", dataPath + "monsterTrucks.megalodon.description", "monsterTrucks/megalodon", 3200, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "monsterTrucks.mohawk.title", dataPath + "monsterTrucks.mohawk.description", "monsterTrucks/mohawk", 1192, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "monsterTrucks.scoobydoo.title", dataPath + "monsterTrucks.scoobydoo.description", "monsterTrucks/scoobydoo", 2186, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "monsterTrucks.usairforce.title", dataPath + "monsterTrucks.usairforce.description", "monsterTrucks/us-airforce", 1077, collectionId, CardClass.Common));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createFootballersCollection(CollectionCategory collectionCategory){

        String dataPath = "data.sports.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "footballers.title");
        collection.setCapImageUrl("footballers/didier-drogba");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "footballers.pirlo.title", dataPath + "footballers.pirlo.description", "footballers/andrea-pirlo", 1090, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "footballers.shevchenko.title", dataPath + "footballers.shevchenko.description", "footballers/andriy-shevchenko", 1079, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "footballers.seedorf.title", dataPath + "footballers.seedorf.description", "footballers/clarence-seedorf", 1160, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "footballers.ronaldo.title", dataPath + "footballers.ronaldo.description", "footballers/cristiano-ronaldo", 3159, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "footballers.beckham.title", dataPath + "footballers.beckham.description", "footballers/david-beckham", 1240, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "footballers.drogba.title", dataPath + "footballers.drogba.description", "footballers/didier-drogba", 1238, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "footballers.maradona.title", dataPath + "footballers.maradona.description", "footballers/diego-maradona", 2327, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "footballers.cruyff.title", dataPath + "footballers.cruyff.description", "footballers/johan-cruyff", 1316, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "footballers.messi.title", dataPath + "footballers.messi.description", "footballers/lionel-messi", 1175, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "footballers.figo.title", dataPath + "footballers.figo.description", "footballers/luis-figo", 1186, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "footballers.kahn.title", dataPath + "footballers.kahn.description", "footballers/oliver-kahn", 1097, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "footballers.raul.title", dataPath + "footballers.raul.description", "footballers/raul", 1038, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "footballers.baggio.title", dataPath + "footballers.baggio.description", "footballers/roberto-baggio", 1195, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "footballers.ronaldinho.title", dataPath + "footballers.ronaldinho.description", "footballers/ronaldinho", 3104, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "footballers.gerrard.title", dataPath + "footballers.gerrard.description", "footballers/steven-gerrard", 1293, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "footballers.henry.title", dataPath + "footballers.henry.description", "footballers/thierry-henry", 1282, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "footballers.basten.title", dataPath + "footballers.basten.description", "footballers/van-basten", 1071, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "footballers.zico.title", dataPath + "footballers.zico.description", "footballers/zico", 1114, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "footballers.zidane.title", dataPath + "footballers.zidane.description", "footballers/zinedine-zidane", 2323, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "footballers.ibrahimovic.title", dataPath + "footballers.ibrahimovic.description", "footballers/zlatan-ibrahimovic", 1031, collectionId, CardClass.Common));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createHalloweenCollection(CollectionCategory collectionCategory){

        String dataPath = "data.epic.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "halloween.title");
        collection.setCapImageUrl("halloween/09");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "halloween.01.title", dataPath + "halloween.01.description", "halloween/01", 1190, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "halloween.02.title", dataPath + "halloween.02.description", "halloween/02", 3080, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "halloween.03.title", dataPath + "halloween.03.description", "halloween/03", 2271, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "halloween.04.title", dataPath + "halloween.04.description", "halloween/04", 1161, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "halloween.05.title", dataPath + "halloween.05.description", "halloween/05", 1052, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "halloween.06.title", dataPath + "halloween.06.description", "halloween/06", 1242, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "halloween.07.title", dataPath + "halloween.07.description", "halloween/07", 1133, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "halloween.08.title", dataPath + "halloween.08.description", "halloween/08", 1123, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "halloween.09.title", dataPath + "halloween.09.description", "halloween/09", 1014, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "halloween.10.title", dataPath + "halloween.10.description", "halloween/10", 1004, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "halloween.11.title", dataPath + "halloween.11.description", "halloween/11", 1195, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "halloween.12.title", dataPath + "halloween.12.description", "halloween/12", 1185, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "halloween.13.title", dataPath + "halloween.13.description", "halloween/13", 2076, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "halloween.14.title", dataPath + "halloween.14.description", "halloween/14", 3166, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "halloween.15.title", dataPath + "halloween.15.description", "halloween/15", 1057, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "halloween.16.title", dataPath + "halloween.16.description", "halloween/16", 1147, collectionId, CardClass.Common));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createEpicMoviesCollection(CollectionCategory collectionCategory){

        String dataPath = "data.epic.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "movies.title");
        collection.setCapImageUrl("epicMovies/300-spartian");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "movies.300spartian.title", dataPath + "movies.300spartian.description", "epicMovies/300-spartian", 1025, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "movies.apocalypto.title", dataPath + "movies.apocalypto.description", "epicMovies/apocalypto", 1301, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "movies.braveheart.title", dataPath + "movies.braveheart.description", "epicMovies/braveheart", 1056, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "movies.fightClub.title", dataPath + "movies.fightClub.description", "epicMovies/fight-club", 3105, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "movies.gladiator.title", dataPath + "movies.gladiator.description", "epicMovies/gladiator", 1357, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "movies.indianaJones.title", dataPath + "movies.indianaJones.description", "epicMovies/indiana-jones", 1098, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "movies.ipman.title", dataPath + "movies.ipman.description", "epicMovies/ipman", 2560, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "movies.kingdomOfHeaven.title", dataPath + "movies.kingdomOfHeaven.description", "epicMovies/kingdom-of-heaven", 1201, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "movies.kingkong.title", dataPath + "movies.kingkong.description", "epicMovies/kingkong", 1146, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "movies.lastOfTheMohicans.title", dataPath + "movies.lastOfTheMohicans.description", "epicMovies/last-of-the-mohicans", 1185, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "movies.madMax.title", dataPath + "movies.madMax.description", "epicMovies/mad-max", 1069, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "movies.piratesOfTheCarib.title", dataPath + "movies.piratesOfTheCarib.description", "epicMovies/pirates-of-the-caribbean", 1037, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "movies.predator.title", dataPath + "movies.predator.description", "epicMovies/predator", 1197, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "movies.terminator.title", dataPath + "movies.terminator.description", "epicMovies/terminator", 1136, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "movies.godfather.title", dataPath + "movies.godfather.description", "epicMovies/the-godfather", 2257, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "movies.lastEmperor.title", dataPath + "movies.lastEmperor.description", "epicMovies/the-last-emperor", 3108, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "movies.mummy.title", dataPath + "movies.mummy.description", "epicMovies/the-mummy", 1137, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "movies.titanic.title", dataPath + "movies.titanic.description", "epicMovies/titanic", 1029, collectionId, CardClass.Common));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createMoneyCollection(CollectionCategory collectionCategory){

        String dataPath = "data.life.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "money.title");
        collection.setCapImageUrl("money/srilanka");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "money.bangladesh.title", dataPath + "money.bangladesh.description", "money/bangladesh", 1097, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "money.brazil.title", dataPath + "money.brazil.description", "money/brazil", 3218, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "money.brunei.title", dataPath + "money.brunei.description", "money/brunei", 1158, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "money.china.title", dataPath + "money.china.description", "money/china", 1067, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "money.dollar.title", dataPath + "money.dollar.description", "money/dollar", 1198, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "money.egypt.title", dataPath + "money.egypt.description", "money/egypt", 1045, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "money.euro.title", dataPath + "money.euro.description", "money/euro", 1170, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "money.fiji.title", dataPath + "money.fiji.description", "money/fiji", 1146, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "money.india.title", dataPath + "money.india.description", "money/india", 1234, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "money.latvia.title", dataPath + "money.latvia.description", "money/latvia", 1220, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "money.mauritania.title", dataPath + "money.mauritania.description", "money/mauritania", 1003, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "money.mongolia.title", dataPath + "money.mongolia.description", "money/mongolia", 2230, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "money.nepal.title", dataPath + "money.nepal.description", "money/nepal", 1340, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "money.nigeria.title", dataPath + "money.nigeria.description", "money/nigeria", 2350, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "money.pound.title", dataPath + "money.pound.description", "money/pound", 1189, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "money.russia.title", dataPath + "money.russia.description", "money/russia", 3239, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "money.srilanka.title", dataPath + "money.srilanka.description", "money/srilanka", 1365, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "money.turkey.title", dataPath + "money.turkey.description", "money/turkey", 1271, collectionId, CardClass.Common));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createWondersCollection(CollectionCategory collectionCategory){

        String dataPath = "data.life.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "wonders.title");
        collection.setCapImageUrl("wonders/easter-island");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "wonders.abuSimbel.title", dataPath + "wonders.abuSimbel.description", "wonders/abu-simbel", 1154, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "wonders.borobudur.title", dataPath + "wonders.borobudur.description", "wonders/borobudur", 2105, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "wonders.chichenItza.title", dataPath + "wonders.chichenItza.description", "wonders/chichen-itza", 1350, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "wonders.chinessWall.title", dataPath + "wonders.chinessWall.description", "wonders/chinese-wall", 1240, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "wonders.colosseum.title", dataPath + "wonders.colosseum.description", "wonders/colosseum", 3097, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "wonders.cristoRedentor.title", dataPath + "wonders.cristoRedentor.description", "wonders/cristo-redentor", 1148, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "wonders.easterIsland.title", dataPath + "wonders.easterIsland.description", "wonders/easter-island", 1167, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "wonders.ephessus.title", dataPath + "wonders.ephessus.description", "wonders/ephessus", 2307, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "wonders.giza.title", dataPath + "wonders.giza.description", "wonders/giza-pyramids", 1247, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "wonders.machuPicchu.title", dataPath + "wonders.machuPicchu.description", "wonders/machu-picchu", 1309, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "wonders.petra.title", dataPath + "wonders.petra.description", "wonders/petra", 1387, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "wonders.pisaTower.title", dataPath + "wonders.pisaTower.description", "wonders/pisa-tower", 1047, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "wonders.stBasilCathedral.title", dataPath + "wonders.stBasilCathedral.description", "wonders/st-basil-cathedral", 1130, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "wonders.stonehenge.title", dataPath + "wonders.stonehenge.description", "wonders/stonehenge", 1157, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "wonders.tajMahal.title", dataPath + "wonders.tajMahal.description", "wonders/taj-mahal", 1205, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "wonders.terracotaArmy.title", dataPath + "wonders.terracotaArmy.description", "wonders/terracota-army", 3197, collectionId, CardClass.Unique));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createAmazingCarsCollection(CollectionCategory collectionCategory ){

        String dataPath = "data.life.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "amazingCars.title");
        collection.setCapImageUrl("amazingCars/astonmartin-vantage");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "amazingCars.astonmartin.title", dataPath + "amazingCars.astonmartin.description", "amazingCars/astonmartin-vantage", 1254, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "amazingCars.audir8.title", dataPath + "amazingCars.audir8.description", "amazingCars/audi-r8", 1310, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "amazingCars.bugattiVeyron.title", dataPath + "amazingCars.bugattiVeyron.description", "amazingCars/bugatti-veyron", 3129, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "amazingCars.dodgeViper.title", dataPath + "amazingCars.dodgeViper.description", "amazingCars/dodge-viper", 1338, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "amazingCars.ferrariF12.title", dataPath + "amazingCars.ferrariF12.description", "amazingCars/ferrari-f12", 1047, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "amazingCars.fordGt.title", dataPath + "amazingCars.fordGt.description", "amazingCars/ford-gt", 1156, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "amazingCars.laferrari.title", dataPath + "amazingCars.laferrari.description", "amazingCars/laferrari", 1365, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "amazingCars.aventador.title", dataPath + "amazingCars.aventador.description", "amazingCars/lamborghini-aventador", 1274, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "amazingCars.huracan.title", dataPath + "amazingCars.huracan.description", "amazingCars/lamborghini-huracan", 2083, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "amazingCars.veneno.title", dataPath + "amazingCars.veneno.description", "amazingCars/lamborghini-veneno", 3192, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "amazingCars.maserati.title", dataPath + "amazingCars.maserati.description", "amazingCars/maserati-granturismo", 1201, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "amazingCars.mclaren720s.title", dataPath + "amazingCars.mclaren720s.description", "amazingCars/mclaren-720s", 1110, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "amazingCars.mclarenP1.title", dataPath + "amazingCars.mclarenP1.description", "amazingCars/mclaren-p1", 1229, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "amazingCars.mercedes.title", dataPath + "amazingCars.mercedes.description", "amazingCars/mercedes-amg-gt-coupe", 1338, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "amazingCars.paganiHuayra.title", dataPath + "amazingCars.paganiHuayra.description", "amazingCars/pagani-huayra", 1147, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "amazingCars.porsche918.title", dataPath + "amazingCars.porsche918.description", "amazingCars/porsche-918-spyder", 1056, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "amazingCars.venomF5.title", dataPath + "amazingCars.venomF5.description", "amazingCars/venom-f5", 2163, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "amazingCars.venomGt.title", dataPath + "amazingCars.venomGt.description", "amazingCars/venom-gt", 1272, collectionId, CardClass.Common));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createPlushToys(CollectionCategory collectionCategory ){

        String dataPath = "data.child.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "plushToys.title");
        collection.setCapImageUrl("plushToys/hero");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "plushToys.alien.title", dataPath + "plushToys.alien.description", "plushToys/alien", 1017, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "plushToys.bowel.title", dataPath + "plushToys.bowel.description", "plushToys/bowel", 1026, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "plushToys.dinosaur.title", dataPath + "plushToys.dinosaur.description", "plushToys/dinosaur", 1135, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "plushToys.dragon.title", dataPath + "plushToys.dragon.description", "plushToys/dragon", 1144, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "plushToys.elephant.title", dataPath + "plushToys.elephant.description", "plushToys/elephant", 1253, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "plushToys.fox.title", dataPath + "plushToys.fox.description", "plushToys/fox", 1262, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "plushToys.heart.title", dataPath + "plushToys.heart.description", "plushToys/heart", 1371, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "plushToys.hero.title", dataPath + "plushToys.hero.description", "plushToys/hero", 1387, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "plushToys.monster.title", dataPath + "plushToys.monster.description", "plushToys/monster", 1098, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "plushToys.nessie.title", dataPath + "plushToys.nessie.description", "plushToys/nessie", 1009, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "plushToys.panda.title", dataPath + "plushToys.panda.description", "plushToys/panda", 1190, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "plushToys.rabbit.title", dataPath + "plushToys.rabbit.description", "plushToys/rabbit", 1181, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "plushToys.robots.title", dataPath + "plushToys.robots.description", "plushToys/robots", 1272, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "plushToys.shark.title", dataPath + "plushToys.shark.description", "plushToys/shark", 1266, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "plushToys.tiger.title", dataPath + "plushToys.tiger.description", "plushToys/tiger", 1354, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "plushToys.troll.title", dataPath + "plushToys.troll.description", "plushToys/troll", 1341, collectionId, CardClass.Common));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createStories(CollectionCategory collectionCategory ){

        String dataPath = "data.child.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "stories.title");
        collection.setCapImageUrl("stories/aliBaba");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "stories.aliBaba.title", dataPath + "stories.aliBaba.description", "stories/aliBaba", 1117, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "stories.aliceInWonderland.title", dataPath + "stories.aliceInWonderland.description", "stories/aliceInWonderland", 1220, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "stories.bremenMusicians.title", dataPath + "stories.bremenMusicians.description", "stories/bremenMusicians", 1137, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "stories.cinderella.title", dataPath + "stories.cinderella.description", "stories/cinderella", 1318, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "stories.donKichot.title", dataPath + "stories.donKichot.description", "stories/donKichot", 2044, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "stories.gulliver.title", dataPath + "stories.gulliver.description", "stories/gulliver", 3151, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "stories.hanselAndGretel.title", dataPath + "stories.hanselAndGretel.description", "stories/hanselAndGretel", 1366, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "stories.littleRed.title", dataPath + "stories.littleRed.description", "stories/littleRed", 1017, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "stories.mobyDick.title", dataPath + "stories.mobyDick.description", "stories/mobyDick", 2138, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "stories.nasreddinHodja.title", dataPath + "stories.nasreddinHodja.description", "stories/nasreddinHodja", 1249, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "stories.peterpan.title", dataPath + "stories.peterpan.description", "stories/peterpan", 1371, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "stories.piedPiper.title", dataPath + "stories.piedPiper.description", "stories/piedPiper", 1083, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "stories.pinokio.title", dataPath + "stories.pinokio.description", "stories/pinokio", 1194, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "stories.pussInBoots.title", dataPath + "stories.pussInBoots.description", "stories/pussInBoots", 3126, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "stories.rapunzel.title", dataPath + "stories.rapunzel.description", "stories/rapunzel", 1207, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "stories.snowWhite.title", dataPath + "stories.snowWhite.description", "stories/snowWhite", 1207, collectionId, CardClass.Common));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createCircus(CollectionCategory collectionCategory ){

        String dataPath = "data.child.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "circus.title");
        collection.setCapImageUrl("circus/dance");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "circus.acrobat.title", dataPath + "circus.acrobat.description", "circus/acrobat", 1097, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "circus.bear.title", dataPath + "circus.bear.description", "circus/bear", 2011, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "circus.bicycle.title", dataPath + "circus.bicycle.description", "circus/bicycle", 3214, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "circus.carousel.title", dataPath + "circus.carousel.description", "circus/carousel", 1254, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "circus.clown.title", dataPath + "circus.clown.description", "circus/clown", 1064, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "circus.dance.title", dataPath + "circus.dance.description", "circus/dance", 1032, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "circus.firehoop.title", dataPath + "circus.firehoop.description", "circus/fire-hoop", 1154, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "circus.flames.title", dataPath + "circus.flames.description", "circus/flames", 3141, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "circus.fortune.title", dataPath + "circus.fortune.description", "circus/fortune", 1074, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "circus.gymnastics.title", dataPath + "circus.gymnastics.description", "circus/gymnastics", 1043, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "circus.juggler.title", dataPath + "circus.juggler.description", "circus/juggler", 1182, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "circus.juggler-team.title", dataPath + "circus.juggler-team.description", "circus/juggler-team", 1291, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "circus.knife.title", dataPath + "circus.knife.description", "circus/knife", 1280, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "circus.suitcase.title", dataPath + "circus.suitcase.description", "circus/suitcase", 1171, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "circus.twins.title", dataPath + "circus.twins.description", "circus/twins", 2030, collectionId, CardClass.Rare));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createSandSculpture(CollectionCategory collectionCategory ){

        String dataPath = "data.life.";

        Collection collection = new Collection();
        collection.setCollectionCategory(collectionCategory);
        collection.setTitle(dataPath + "sand.title");
        collection.setCapImageUrl("sand/shark");
        collectionRepository.save(collection);

        int collectionId = collection.getId();

        List<Card> cardList = new ArrayList();

        cardList.add(new Card(dataPath + "sand.affricanSpirit.title", dataPath + "sand.affricanSpirit.description", "sand/affrican-spirit", 2200, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "sand.baby.title", dataPath + "sand.baby.description", "sand/baby", 1229, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "sand.carting.title", dataPath + "sand.carting.description", "sand/carting", 1148, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "sand.chineseMonsters.title", dataPath + "sand.chineseMonsters.description", "sand/chinese-monsters", 1157, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "sand.commander.title", dataPath + "sand.commander.description", "sand/commander", 1061, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "sand.dragon.title", dataPath + "sand.dragon.description", "sand/dragon", 1072, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "sand.fetus.title", dataPath + "sand.fetus.description", "sand/fetus", 1183, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "sand.gulliver.title", dataPath + "sand.gulliver.description", "sand/gulliver", 1194, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "sand.motherDragon.title", dataPath + "sand.motherDragon.description", "sand/mother-dragon", 1205, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "sand.mouse.title", dataPath + "sand.mouse.description", "sand/mouse", 1316, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "sand.princess.title", dataPath + "sand.princess.description", "sand/princess", 3127, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "sand.redindian.title", dataPath + "sand.redindian.description", "sand/red-indian", 2338, collectionId, CardClass.Rare));
        cardList.add(new Card(dataPath + "sand.safari.title", dataPath + "sand.safari.description", "sand/safari", 1249, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "sand.sailors.title", dataPath + "sand.sailors.description", "sand/sailors", 1050, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "sand.shark.title", dataPath + "sand.shark.description", "sand/shark", 1161, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "sand.smash.title", dataPath + "sand.smash.description", "sand/smash", 1272, collectionId, CardClass.Common));
        cardList.add(new Card(dataPath + "sand.viking.title", dataPath + "sand.viking.description", "sand/viking", 3183, collectionId, CardClass.Unique));
        cardList.add(new Card(dataPath + "sand.warriors.title", dataPath + "sand.warriors.description", "sand/warriors", 1394, collectionId, CardClass.Common));

        cardRepository.saveAll(cardList);

        collection.setNumOfCards(cardList.size());
        collectionRepository.save(collection);
    }

    private void createAchievements(){

        List<Achievement> achievementList = new ArrayList();
        achievementList.add(new Achievement("data.achievements.beginner.title", "data.achievements.beginner.description", "TotalCardsOpened=150", "achievements/beginner.png", 15, 4, 1));
        achievementList.add(new Achievement("data.achievements.collector.title", "data.achievements.collector.description", null, "achievements/collector.png", 1, 1, 1));
        achievementList.add(new Achievement("data.achievements.experienced.title", "data.achievements.experienced.description", "TotalRareCardsOpened=50", "achievements/experienced.png", 20, 5, 1));
        achievementList.add(new Achievement("data.achievements.expert.title", "data.achievements.expert.description", "TotalUniqueCardsOpened=15", "achievements/expert.png", 25, 10, 3));
        achievementList.add(new Achievement("data.achievements.hero.title", "data.achievements.hero.description", "TotalPoints=1000000", "achievements/hero.png", 30, 15, 3));
        achievementList.add(new Achievement("data.achievements.signUp.title", "data.achievements.signUp.description", null, "achievements/signup.png", 10, 2, 1));
        achievementList.add(new Achievement("data.achievements.facebook.title", "data.achievements.facebook.description", null, "achievements/facebook.png", 15, 3, 1));

        achievementRepository.saveAll(achievementList);
    }
}