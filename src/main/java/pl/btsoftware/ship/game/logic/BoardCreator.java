package pl.btsoftware.ship.game.logic;

import java.util.ArrayList;
import java.util.List;

class BoardCreator {
    static Board create() {
        Board board = new Board();
        board.addFields(createFirstLine());
        board.addFields(createSecondLine());
        board.addFields(createThirdLine());
        board.addFields(createFourthLine());
        board.addFields(createFifthLine());
        board.addFields(createSixthLine());
        board.addFields(createSeventhLine());
        board.addFields(createEighthLine());
        board.addFields(createNinthLine());

        return board;
    }

    private static List<Field> createFirstLine() {
        List<Field> fields = new ArrayList<>();
        int line = 1;
        fields.add(createField(1, line, 770, null));
        fields.add(createField(2, line, 760, null));
        fields.add(createField(3, line, 750, null));
        fields.add(createField(4, line, 750, null));
        fields.add(createField(5, line, 740, null));
        fields.add(createField(6, line, 740, null));
        fields.add(createField(7, line, 750, null));
        fields.add(createField(8, line, 760, null));

        return fields;
    }

    private static List<Field> createSecondLine() {
        List<Field> fields = new ArrayList<>();
        int line = 2;
        fields.add(createField(1, line, 810, null));
        fields.add(createField(2, line, 840, bottle(1)));
        fields.add(createField(3, line, 830, bottle(2)));
        fields.add(createField(4, line, 820, adventure(3)));
        fields.add(createField(5, line, 800, null));
        fields.add(createField(6, line, 810, bottle(4)));
        fields.add(createField(7, line, 830, null));
        fields.add(createField(8, line, 830, bottle(5)));

        return fields;
    }

    private static List<Field> createThirdLine() {
        List<Field> fields = new ArrayList<>();
        int line = 3;
        fields.add(createField(1, line, 860, null));
        fields.add(createField(2, line, 870, adventure(6)));
        fields.add(createField(3, line, 860, bottle(7)));
        fields.add(createField(4, line, 860, bottle(8)));
        fields.add(createField(5, line, 850, adventure(9)));
        fields.add(createField(6, line, 870, bottle(10)));
        fields.add(createField(7, line, 870, null));
        fields.add(createField(8, line, 880, adventure(11)));

        return fields;
    }

    private static List<Field> createFourthLine() {
        List<Field> fields = new ArrayList<>();
        int line = 4;
        fields.add(createField(1, line, 950, bottle(12)));
        fields.add(createField(2, line, 940, null));
        fields.add(createField(3, line, 940, treasure(36)));
        fields.add(createField(4, line, 950, adventure(13)));
        fields.add(createField(5, line, 930, adventure(14)));
        fields.add(createField(6, line, 930, null));
        fields.add(createField(7, line, 920, bottle(15)));
        fields.add(createField(8, line, 910, treasure(37)));

        return fields;
    }

    private static List<Field> createFifthLine() {
        List<Field> fields = new ArrayList<>();
        int line = 5;
        fields.add(createField(1, line, 1000, null));
        fields.add(createField(2, line, 1000, adventure(16)));
        fields.add(createField(3, line, 1010, bottle(17)));
        fields.add(createField(4, line, 1030, bottle(18)));
        fields.add(createField(5, line, 1020, treasure(38)));
        fields.add(createField(6, line, 1040, adventure(19)));
        fields.add(createField(7, line, 1050, bottle(20)));
        fields.add(createField(8, line, 1020, bottle(21)));

        return fields;
    }

    private static List<Field> createSixthLine() {
        List<Field> fields = new ArrayList<>();
        int line = 6;
        fields.add(createField(1, line, 1050, treasure(39)));
        fields.add(createField(2, line, 1060, null));
        fields.add(createField(3, line, 1050, bottle(22)));
        fields.add(createField(4, line, 1080, treasure(40)));
        fields.add(createField(5, line, 1070, adventure(23)));
        fields.add(createField(6, line, 1100, bottle(24)));
        fields.add(createField(7, line, 1100, null));
        fields.add(createField(8, line, 1080, treasure(41)));

        return fields;
    }

    private static List<Field> createSeventhLine() {
        List<Field> fields = new ArrayList<>();
        int line = 7;
        fields.add(createField(1, line, 1110, null));
        fields.add(createField(2, line, 1120, adventure(25)));
        fields.add(createField(3, line, 1150, null));
        fields.add(createField(4, line, 1110, bottle(26)));
        fields.add(createField(5, line, 1120, adventure(27)));
        fields.add(createField(6, line, 1130, bottle(28)));
        fields.add(createField(7, line, 1160, bottle(29)));
        fields.add(createField(8, line, 1150, null));

        return fields;
    }

    private static List<Field> createEighthLine() {
        List<Field> fields = new ArrayList<>();
        int line = 8;
        fields.add(createField(1, line, 1200, null));
        fields.add(createField(2, line, 1200, bottle(30)));
        fields.add(createField(3, line, 1210, bottle(31)));
        fields.add(createField(4, line, 1200, adventure(32)));
        fields.add(createField(5, line, 1190, bottle(33)));
        fields.add(createField(6, line, 1190, adventure(34)));
        fields.add(createField(7, line, 1210, adventure(35)));
        fields.add(createField(8, line, 1200, null));

        return fields;
    }

    private static List<Field> createNinthLine() {
        List<Field> fields = new ArrayList<>();
        int line = 9;
        fields.add(createField(1, line, 1270, null));
        fields.add(createField(2, line, 1280, null));
        fields.add(createField(3, line, 1300, null));
        fields.add(createField(4, line, 1250, null));
        fields.add(createField(5, line, 1270, adventure(36)));
        fields.add(createField(6, line, 1250, null));
        fields.add(createField(7, line, 1350, null));
        fields.add(createField(8, line, 1280, null));

        return fields;
    }

    private static Field createField(int x, int y, int maxWeight, SpecialField specialField) {
        boolean isStartPoint = y == 1;
        boolean isEndPoint = y == 9 && (x == 4 || x == 5);
        return new Field(x, y, maxWeight, specialField, isStartPoint, isEndPoint);
    }

    private static SpecialField bottle(int number) {
        return new SpecialField(SpecialFieldKind.BOTTLE, number);
    }

    private static SpecialField adventure(int number) {
        return new SpecialField(SpecialFieldKind.ADVENTURE, number);
    }

    private static SpecialField treasure(int number) {
        return new SpecialField(SpecialFieldKind.TREASURE, number);
    }
}
