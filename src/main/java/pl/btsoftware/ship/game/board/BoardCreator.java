package pl.btsoftware.ship.game.board;

import java.util.ArrayList;
import java.util.List;

class BoardCreator {
    static Board create() {
        Board board = new Board();
        board.createRow(createFirstLine());
        board.createRow(createSecondLine());
        board.createRow(createThirdLine());
        board.createRow(createFourthLine());
        board.createRow(createFifthLine());
        board.createRow(createSixthLine());
        board.createRow(createSeventhLine());
        board.createRow(createEighthLine());
        board.createRow(createNinthLine());

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
        fields.add(createField(2, line, 840, bottle()));
        fields.add(createField(3, line, 830, bottle()));
        fields.add(createField(4, line, 820, adventure()));
        fields.add(createField(5, line, 800, null));
        fields.add(createField(6, line, 810, bottle()));
        fields.add(createField(7, line, 830, null));
        fields.add(createField(8, line, 830, bottle()));

        return fields;
    }

    private static List<Field> createThirdLine() {
        List<Field> fields = new ArrayList<>();
        int line = 3;
        fields.add(createField(1, line, 860, null));
        fields.add(createField(2, line, 870, adventure()));
        fields.add(createField(3, line, 860, bottle()));
        fields.add(createField(4, line, 860, bottle()));
        fields.add(createField(5, line, 850, adventure()));
        fields.add(createField(6, line, 870, bottle()));
        fields.add(createField(7, line, 870, null));
        fields.add(createField(8, line, 880, adventure()));

        return fields;
    }

    private static List<Field> createFourthLine() {
        List<Field> fields = new ArrayList<>();
        int line = 4;
        fields.add(createField(1, line, 950, bottle()));
        fields.add(createField(2, line, 940, null));
        fields.add(createField(3, line, 940, treasure()));
        fields.add(createField(4, line, 950, adventure()));
        fields.add(createField(5, line, 930, adventure()));
        fields.add(createField(6, line, 930, null));
        fields.add(createField(7, line, 920, bottle()));
        fields.add(createField(8, line, 910, treasure()));

        return fields;
    }

    private static List<Field> createFifthLine() {
        List<Field> fields = new ArrayList<>();
        int line = 5;
        fields.add(createField(1, line, 1000, null));
        fields.add(createField(2, line, 1000, adventure()));
        fields.add(createField(3, line, 1010, bottle()));
        fields.add(createField(4, line, 1030, bottle()));
        fields.add(createField(5, line, 1020, treasure()));
        fields.add(createField(6, line, 1040, adventure()));
        fields.add(createField(7, line, 1050, bottle()));
        fields.add(createField(8, line, 1020, bottle()));

        return fields;
    }

    private static List<Field> createSixthLine() {
        List<Field> fields = new ArrayList<>();
        int line = 6;
        fields.add(createField(1, line, 1050, treasure()));
        fields.add(createField(2, line, 1060, null));
        fields.add(createField(3, line, 1050, bottle()));
        fields.add(createField(4, line, 1080, treasure()));
        fields.add(createField(5, line, 1070, adventure()));
        fields.add(createField(6, line, 1100, bottle()));
        fields.add(createField(7, line, 1100, null));
        fields.add(createField(8, line, 1080, treasure()));

        return fields;
    }

    private static List<Field> createSeventhLine() {
        List<Field> fields = new ArrayList<>();
        int line = 7;
        fields.add(createField(1, line, 1110, null));
        fields.add(createField(2, line, 1120, adventure()));
        fields.add(createField(3, line, 1150, null));
        fields.add(createField(4, line, 1110, bottle()));
        fields.add(createField(5, line, 1120, adventure()));
        fields.add(createField(6, line, 1130, bottle()));
        fields.add(createField(7, line, 1160, bottle()));
        fields.add(createField(8, line, 1150, null));

        return fields;
    }

    private static List<Field> createEighthLine() {
        List<Field> fields = new ArrayList<>();
        int line = 8;
        fields.add(createField(1, line, 1200, null));
        fields.add(createField(2, line, 1200, bottle()));
        fields.add(createField(3, line, 1210, bottle()));
        fields.add(createField(4, line, 1200, adventure()));
        fields.add(createField(5, line, 1190, bottle()));
        fields.add(createField(6, line, 1190, adventure()));
        fields.add(createField(7, line, 1210, adventure()));
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
        fields.add(createField(5, line, 1270, adventure()));
        fields.add(createField(6, line, 1250, null));
        fields.add(createField(7, line, 1350, null));
        fields.add(createField(8, line, 1280, null));

        return fields;
    }

    private static Field createField(int x, int y, int maxWeight, Field.SpecialField specialField) {
        boolean isStartPoint = y == 1;
        boolean isEndPoint = y == 9 && (x == 4 || x == 5);
        return new Field(x, y, maxWeight, specialField, isStartPoint, isEndPoint);
    }

    private static Field.SpecialField bottle() {
        return new Field.SpecialField(SpecialFieldKind.BOTTLE);
    }

    private static Field.SpecialField adventure() {
        return new Field.SpecialField(SpecialFieldKind.ADVENTURE);
    }

    private static Field.SpecialField treasure() {
        return new Field.SpecialField(SpecialFieldKind.TREASURE);
    }
}
