package Task_3_Copilot_Code_Generation; /**
 * Task 3 - Code Generated from Copilot
 * Promped used: Load your model into your favourite Gen AI tool as an image (e.g., png) or as semi-structured data (e.g., XML) 
 * and ask it to generate program code for you. Please do that.
 * Attached png of the team UML diagram.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// ======================= ENUMS & INTERFACES =======================

enum ResourceType {
    BRICK,
    ORE,
    SHEEP,
    LUMBER,
    WHEAT,
    DESERT
}

interface Card {
    String getCardType();
}

interface Dice {
    int roll();
}

// ======================= CORE GAME CLASSES =======================

class Game {
    private int maxRounds;
    private int currentRound;
    private Board board;
    private List<Trader> agents; // multiplicity 3..4
    private Trader bank;
    private Dice dice;

    public Game(int rounds) {
        this.maxRounds = rounds;
        this.currentRound = 0;
        this.board = new Board();
        this.agents = new ArrayList<>();
        this.bank = new Bank();
        this.dice = new MultiDice(2);
    }

    public void run() {
        // main game loop skeleton
        while (currentRound < maxRounds) {
            currentRound++;
            produceResource();
            // player turns, building, trading, etc.
        }
    }

    public void produceResource() {
        // TODO: implement resource production logic
    }

    public void build() {
        // TODO: implement building logic
    }

    // Getters/setters as needed...
}

// ======================= BOARD & MAP STRUCTURE =======================

class Board {
    private HexTile[] tiles; // [19]

    public Board() {
        this.tiles = new HexTile[19];
        // TODO: initialize tiles with resources, tokens, locations, etc.
    }

    public ResourceType getResourceProdTile(Location location) {
        // TODO: find tile by location and return its resource type
        return null;
    }
}

class HexTile {
    private ResourceType resource;
    private int tokenTile;
    private int token;
    private Location[] nodes; // [6]
    private Location[] edges; // [6]

    public HexTile(ResourceType resource, int token) {
        this.resource = resource;
        this.token = token;
        this.nodes = new Location[6];
        this.edges = new Location[6];
    }

    public Location getNode(int index) {
        return nodes[index];
    }

    public Location getEdge(int index) {
        return edges[index];
    }

    public ResourceType getResourceType() {
        return resource;
    }

    public int getToken() {
        return token;
    }

    public int getTokenTile() {
        return tokenTile;
    }
}

class Location {
    private boolean occupied;

    public Location() {
        this.occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied() {
        this.occupied = true;
    }
}

class Edge {
    private int start;
    private int end;

    public Edge(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Node {
    private int id;

    public Node(int id) {
        this.id = id;
    }
}

// ======================= AGENTS & INFRASTRUCTURE =======================

class Agent {
    private int victoryPoints;
    private Infrastructure buildingsOwned;          // [1] (could be a main building or similar)
    private List<Infrastructure> roadsOwned;        // [*]
    private List<Edge> roadList;                    // [1] logically a list
    private List<Integer> citiesList;               // [1] logically a list of node IDs
    private List<Integer> settlementList;           // [1] logically a list of node IDs

    public Agent() {
        this.victoryPoints = 0;
        this.roadsOwned = new ArrayList<>();
        this.roadList = new ArrayList<>();
        this.citiesList = new ArrayList<>();
        this.settlementList = new ArrayList<>();
    }

    public void buildRoad() {
        // TODO: implement road building
    }

    public void buildCity() {
        // TODO: implement city building
    }

    public void buildSettlement() {
        // TODO: implement settlement building
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    protected void addVictoryPoints(int points) {
        this.victoryPoints += points;
    }
}

class Infrastructure {
    private int victoryPoints;
    private Agent owner;
    private Location location;

    public Infrastructure(int victoryPoints, Location location, Agent owner) {
        this.victoryPoints = victoryPoints;
        this.location = location;
        this.owner = owner;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public Agent getOwner() {
        return owner;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}

class City extends Infrastructure {
    public City(Agent owner, Location location) {
        super(2, location, owner); // typical Catan city = 2 VP
        owner.addVictoryPoints(getVictoryPoints());
    }
}

class Settlement extends Infrastructure {
    public Settlement(Agent owner, Location location) {
        super(1, location, owner); // typical Catan settlement = 1 VP
        owner.addVictoryPoints(getVictoryPoints());
    }
}

class Road extends Infrastructure {
    public Road(Agent owner, Location location) {
        super(0, location, owner); // roads usually 0 VP
    }
}

// ======================= TRADING & CARDS =======================

class Trader {
    private int[] resourceCount;      // [5]
    private List<Card> resourceCard;  // [*]

    public Trader() {
        this.resourceCount = new int[5];
        this.resourceCard = new ArrayList<>();
    }

    public void addCard(Card newCard) {
        resourceCard.add(newCard);
        if (newCard instanceof ResourceCard rc) {
            incrementResource(rc.getResourceType());
        }
    }

    public Card removeCard() {
        if (resourceCard.isEmpty()) {
            return null;
        }
        Card card = resourceCard.remove(resourceCard.size() - 1);
        if (card instanceof ResourceCard rc) {
            decrementResource(rc.getResourceType());
        }
        return card;
    }

    private void incrementResource(ResourceType type) {
        int index = resourceIndex(type);
        if (index >= 0) resourceCount[index]++;
    }

    private void decrementResource(ResourceType type) {
        int index = resourceIndex(type);
        if (index >= 0 && resourceCount[index] > 0) resourceCount[index]--;
    }

    private int resourceIndex(ResourceType type) {
        return switch (type) {
            case BRICK -> 0;
            case ORE -> 1;
            case SHEEP -> 2;
            case LUMBER -> 3;
            case WHEAT -> 4;
            case DESERT -> -1; // usually no cards for desert
        };
    }
}

class Bank extends Trader {
    public Bank() {
        super();
    }
}

class ResourceCard implements Card {
    private ResourceType resourceType;

    public ResourceCard(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    @Override
    public String getCardType() {
        return "Resource: " + resourceType.name();
    }
}

// ======================= DICE & RANDOM GENERATION =======================

class RandomIntGen {
    private int numOfIndices;
    private Random random;

    public RandomIntGen(int numOfIndices) {
        this.numOfIndices = numOfIndices;
        this.random = new Random();
    }

    public int roll() {
        // returns value in [1, numOfIndices]
        return random.nextInt(numOfIndices) + 1;
    }
}

class RegularDie implements Dice {
    private RandomIntGen random;

    public RegularDie() {
        this.random = new RandomIntGen(6);
    }

    @Override
    public int roll() {
        return random.roll();
    }
}

class MultiDice implements Dice {
    private Dice[] diceSet;

    public MultiDice(int numOfDice) {
        this.diceSet = new Dice[numOfDice];
        for (int i = 0; i < numOfDice; i++) {
            diceSet[i] = new RegularDie();
        }
    }

    @Override
    public int roll() {
        int sum = 0;
        for (Dice d : diceSet) {
            sum += d.roll();
        }
        return sum;
    }
}