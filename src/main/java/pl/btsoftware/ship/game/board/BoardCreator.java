package pl.btsoftware.ship.game.board;

import pl.btsoftware.ship.game.events.EventField;
import pl.btsoftware.ship.game.events.FieldId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class BoardCreator {
    static Board create(List<PlayerSituation> playersInGame, List<EventField> eventFields) {
        Board board = new Board();
        board.createRow(createFirstLine(playersInGame, eventFields));
        board.createRow(createSecondLine(playersInGame, eventFields));
        board.createRow(createThirdLine(playersInGame, eventFields));
        board.createRow(createFourthLine(playersInGame, eventFields));
        board.createRow(createFifthLine(playersInGame, eventFields));
        board.createRow(createSixthLine(playersInGame, eventFields));
        board.createRow(createSeventhLine(playersInGame, eventFields));
        board.createRow(createEighthLine(playersInGame, eventFields));
        board.createRow(createNinthLine(playersInGame, eventFields));

        return board;
    }

    private static List<Field> createFirstLine(List<PlayerSituation> playersInGame, List<EventField> events) {
        List<Field> fields = new ArrayList<>();
        int line = 1;
        fields.add(createField(1, line, 770, playersInGame, events));
        fields.add(createField(2, line, 760, playersInGame, events));
        fields.add(createField(3, line, 750, playersInGame, events));
        fields.add(createField(4, line, 750, playersInGame, events));
        fields.add(createField(5, line, 740, playersInGame, events));
        fields.add(createField(6, line, 740, playersInGame, events));
        fields.add(createField(7, line, 750, playersInGame, events));
        fields.add(createField(8, line, 760, playersInGame, events));

        return fields;
    }

    private static List<Field> createSecondLine(List<PlayerSituation> playersInGame, List<EventField> events) {
        List<Field> fields = new ArrayList<>();
        int line = 2;
        fields.add(createField(1, line, 810, playersInGame, events));
        fields.add(createField(2, line, 840, playersInGame, events));
        fields.add(createField(3, line, 830, playersInGame, events));
        fields.add(createField(4, line, 820, playersInGame, events));
        fields.add(createField(5, line, 800, playersInGame, events));
        fields.add(createField(6, line, 810, playersInGame, events));
        fields.add(createField(7, line, 830, playersInGame, events));
        fields.add(createField(8, line, 830, playersInGame, events));

        return fields;
    }

    private static List<Field> createThirdLine(List<PlayerSituation> playersInGame, List<EventField> events) {
        List<Field> fields = new ArrayList<>();
        int line = 3;
        fields.add(createField(1, line, 860, playersInGame, events));
        fields.add(createField(2, line, 870, playersInGame, events));
        fields.add(createField(3, line, 860, playersInGame, events));
        fields.add(createField(4, line, 860, playersInGame, events));
        fields.add(createField(5, line, 850, playersInGame, events));
        fields.add(createField(6, line, 870, playersInGame, events));
        fields.add(createField(7, line, 870, playersInGame, events));
        fields.add(createField(8, line, 880, playersInGame, events));

        return fields;
    }

    private static List<Field> createFourthLine(List<PlayerSituation> playersInGame, List<EventField> events) {
        List<Field> fields = new ArrayList<>();
        int line = 4;
        fields.add(createField(1, line, 950, playersInGame, events));
        fields.add(createField(2, line, 940, playersInGame, events));
        fields.add(createField(3, line, 940, playersInGame, events));
        fields.add(createField(4, line, 950, playersInGame, events));
        fields.add(createField(5, line, 930, playersInGame, events));
        fields.add(createField(6, line, 930, playersInGame, events));
        fields.add(createField(7, line, 920, playersInGame, events));
        fields.add(createField(8, line, 910, playersInGame, events));

        return fields;
    }

    private static List<Field> createFifthLine(List<PlayerSituation> playersInGame, List<EventField> events) {
        List<Field> fields = new ArrayList<>();
        int line = 5;
        fields.add(createField(1, line, 1000, playersInGame, events));
        fields.add(createField(2, line, 1000, playersInGame, events));
        fields.add(createField(3, line, 1010, playersInGame, events));
        fields.add(createField(4, line, 1030, playersInGame, events));
        fields.add(createField(5, line, 1020, playersInGame, events));
        fields.add(createField(6, line, 1040, playersInGame, events));
        fields.add(createField(7, line, 1050, playersInGame, events));
        fields.add(createField(8, line, 1020, playersInGame, events));

        return fields;
    }

    private static List<Field> createSixthLine(List<PlayerSituation> playersInGame, List<EventField> events) {
        List<Field> fields = new ArrayList<>();
        int line = 6;
        fields.add(createField(1, line, 1050, playersInGame, events));
        fields.add(createField(2, line, 1060, playersInGame, events));
        fields.add(createField(3, line, 1050, playersInGame, events));
        fields.add(createField(4, line, 1080, playersInGame, events));
        fields.add(createField(5, line, 1070, playersInGame, events));
        fields.add(createField(6, line, 1100, playersInGame, events));
        fields.add(createField(7, line, 1100, playersInGame, events));
        fields.add(createField(8, line, 1080, playersInGame, events));

        return fields;
    }

    private static List<Field> createSeventhLine(List<PlayerSituation> playersInGame, List<EventField> events) {
        List<Field> fields = new ArrayList<>();
        int line = 7;
        fields.add(createField(1, line, 1110, playersInGame, events));
        fields.add(createField(2, line, 1120, playersInGame, events));
        fields.add(createField(3, line, 1150, playersInGame, events));
        fields.add(createField(4, line, 1110, playersInGame, events));
        fields.add(createField(5, line, 1120, playersInGame, events));
        fields.add(createField(6, line, 1130, playersInGame, events));
        fields.add(createField(7, line, 1160, playersInGame, events));
        fields.add(createField(8, line, 1150, playersInGame, events));

        return fields;
    }

    private static List<Field> createEighthLine(List<PlayerSituation> playersInGame, List<EventField> events) {
        List<Field> fields = new ArrayList<>();
        int line = 8;
        fields.add(createField(1, line, 1200, playersInGame, events));
        fields.add(createField(2, line, 1200, playersInGame, events));
        fields.add(createField(3, line, 1210, playersInGame, events));
        fields.add(createField(4, line, 1200, playersInGame, events));
        fields.add(createField(5, line, 1190, playersInGame, events));
        fields.add(createField(6, line, 1190, playersInGame, events));
        fields.add(createField(7, line, 1210, playersInGame, events));
        fields.add(createField(8, line, 1200, playersInGame, events));

        return fields;
    }

    private static List<Field> createNinthLine(List<PlayerSituation> playersInGame, List<EventField> events) {
        List<Field> fields = new ArrayList<>();
        int line = 9;
        fields.add(createField(1, line, 1270, playersInGame, events));
        fields.add(createField(2, line, 1280, playersInGame, events));
        fields.add(createField(3, line, 1300, playersInGame, events));
        fields.add(createField(4, line, 1250, playersInGame, events));
        fields.add(createField(5, line, 1270, playersInGame, events));
        fields.add(createField(6, line, 1250, playersInGame, events));
        fields.add(createField(7, line, 1350, playersInGame, events));
        fields.add(createField(8, line, 1280, playersInGame, events));

        return fields;
    }

    private static Field createField(int x, int y, int maxWeight, List<PlayerSituation> playersInGame, List<EventField> events) {
        boolean isStartPoint = y == 1;
        boolean isEndPoint = y == 9 && (x == 4 || x == 5);
        return new Field(x, y, maxWeight,
                events.stream().filter(event -> event.fieldId().equals(new FieldId(x, y))).map(event -> new Field.SpecialField(event.kind())).findFirst().orElse(null),
                playersInGame.stream().filter(playerSituation -> playerSituation.getCoordinates() != null && playerSituation.getCoordinates().equals(new PositionOnBoard(x, y))).map(playerSituation -> new Field.Player(playerSituation.getPlayerName().getName(), playerSituation.getCountry())).collect(Collectors.toList()),
                isStartPoint, isEndPoint);
    }
}
