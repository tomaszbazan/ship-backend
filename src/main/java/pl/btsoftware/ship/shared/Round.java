package pl.btsoftware.ship.shared;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Round {
    PREPARING, ROUND_1_MOVE, ROUND_2_MOVE, ROUND_3_MOVE, ROUND_4_MOVE, ROUND_5_MOVE,
    ROUND_6_MOVE, ROUND_7_MOVE, ROUND_8_MOVE, ROUND_9_MOVE, ROUND_10_MOVE, ROUND_11_MOVE,
    ROUND_12_MOVE, FINISHED;
//        PREPARING, ROUND_1_MOVE, ROUND_1_TRADING, ROUND_2_MOVE, ROUND_2_TRADING,
//        ROUND_3_MOVE, ROUND_3_TRADING, ROUND_4_MOVE, ROUND_4_TRADING, ROUND_5_MOVE, ROUND_5_TRADING,
//        ROUND_6_MOVE, ROUND_6_TRADING, ROUND_7_MOVE, ROUND_7_TRADING, ROUND_8_MOVE, ROUND_8_TRADING,
//        ROUND_9_MOVE, ROUND_9_TRADING, ROUND_10_MOVE, ROUND_10_TRADING, ROUND_11_MOVE, ROUND_11_TRADING,
//        ROUND_12_MOVE, ROUND_12_TRADING, FINISHED;

    public Round next() {
        return switch (this) {
            case PREPARING -> ROUND_1_MOVE;
            case ROUND_1_MOVE -> ROUND_2_MOVE;
            case ROUND_2_MOVE -> ROUND_3_MOVE;
            case ROUND_3_MOVE -> ROUND_4_MOVE;
            case ROUND_4_MOVE -> ROUND_5_MOVE;
            case ROUND_5_MOVE -> ROUND_6_MOVE;
            case ROUND_6_MOVE -> ROUND_7_MOVE;
            case ROUND_7_MOVE -> ROUND_8_MOVE;
            case ROUND_8_MOVE -> ROUND_9_MOVE;
            case ROUND_9_MOVE -> ROUND_10_MOVE;
            case ROUND_10_MOVE -> ROUND_11_MOVE;
            case ROUND_11_MOVE -> ROUND_12_MOVE;
            case ROUND_12_MOVE -> FINISHED;
//                case ROUND_1_MOVE -> ROUND_1_TRADING;
//                case ROUND_1_TRADING -> ROUND_2_MOVE;
//                case ROUND_2_MOVE -> ROUND_2_TRADING;
//                case ROUND_2_TRADING -> ROUND_3_MOVE;
//                case ROUND_3_MOVE -> ROUND_3_TRADING;
//                case ROUND_3_TRADING -> ROUND_4_MOVE;
//                case ROUND_4_MOVE -> ROUND_4_TRADING;
//                case ROUND_4_TRADING -> ROUND_5_MOVE;
//                case ROUND_5_MOVE -> ROUND_5_TRADING;
//                case ROUND_5_TRADING -> ROUND_6_MOVE;
//                case ROUND_6_MOVE -> ROUND_6_TRADING;
//                case ROUND_6_TRADING -> ROUND_7_MOVE;
//                case ROUND_7_MOVE -> ROUND_7_TRADING;
//                case ROUND_7_TRADING -> ROUND_8_MOVE;
//                case ROUND_8_MOVE -> ROUND_8_TRADING;
//                case ROUND_8_TRADING -> ROUND_9_MOVE;
//                case ROUND_9_MOVE -> ROUND_9_TRADING;
//                case ROUND_9_TRADING -> ROUND_10_MOVE;
//                case ROUND_10_MOVE -> ROUND_10_TRADING;
//                case ROUND_10_TRADING -> ROUND_11_MOVE;
//                case ROUND_11_MOVE -> ROUND_11_TRADING;
//                case ROUND_11_TRADING -> ROUND_12_MOVE;
//                case ROUND_12_MOVE -> ROUND_12_TRADING;
//                case ROUND_12_TRADING -> FINISHED;
            default -> throw new IllegalStateException("Unexpected value: " + this);
        };
    }
}
