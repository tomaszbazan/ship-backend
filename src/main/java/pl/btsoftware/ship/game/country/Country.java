package pl.btsoftware.ship.game.country;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Country {
    JAMAICA("Jamaica"),
    HAITI("Haiti"),
    CUBA("Cuba"),
    GUATEMALA("Guatemala"),
    PUERTO_RICO("Puerto Rico");

    private final String name;

    public static Country from(String text) {
        for (Country b : Country.values()) {
            if (b.getName().equals(text)) {
                return b;
            }
        }
        return null;
    }
//
//    private static Map<GoodsType, Integer> jamaicaStartGoods() {
//        return Stream.of(new Object[][]{
//                {SUGARCANE, 100},
//                {TOBACCO, 170},
//                {CITRUS, 80},
//                {TEA, 280},
//                {GOLD, 163}
//        }).collect(Collectors.toMap(data -> (GoodsType) data[0], data -> (Integer) data[1]));
//    }
//
//    private static Map<GoodsType, Integer> jamaicaOrder() {
//        return Stream.of(new Object[][]{
//                {COFFEE, 240},
//                {COCOA, 240},
//                {SUGARCANE, 80},
//                {COTTON, 120},
//                {TOBACCO, 20},
//                {TEA, 200},
//                {VANILLA, 70}
//        }).collect(Collectors.toMap(data -> (GoodsType) data[0], data -> (Integer) data[1]));
//    }
//
//    private static Map<GoodsType, Integer> haitiStartGoods() {
//        return Stream.of(new Object[][]{
//                {COFFEE, 240},
//                {COCOA, 240},
//                {SUGARCANE, 80},
//                {COTTON, 120},
//                {TOBACCO, 20},
//                {TEA, 200},
//                {VANILLA, 70}
//        }).collect(Collectors.toMap(data -> (GoodsType) data[0], data -> (Integer) data[1]));
//    }
//
//    private static Map<GoodsType, Integer> haitiOrder() {
//        return Stream.of(new Object[][]{
//                {COFFEE, 240},
//                {COCOA, 240},
//                {SUGARCANE, 80},
//                {COTTON, 120},
//                {TOBACCO, 20},
//                {TEA, 200},
//                {VANILLA, 70}
//        }).collect(Collectors.toMap(data -> (GoodsType) data[0], data -> (Integer) data[1]));
//    }
//
//    private static Map<GoodsType, Integer> cubaStartGoods() {
//        return Stream.of(new Object[][]{
//                {COFFEE, 240},
//                {COCOA, 240},
//                {SUGARCANE, 80},
//                {COTTON, 120},
//                {TOBACCO, 20},
//                {TEA, 200},
//                {VANILLA, 70}
//        }).collect(Collectors.toMap(data -> (GoodsType) data[0], data -> (Integer) data[1]));
//    }
//
//    private static Map<GoodsType, Integer> cubaOrder() {
//        return Stream.of(new Object[][]{
//                {COFFEE, 240},
//                {COCOA, 240},
//                {SUGARCANE, 80},
//                {COTTON, 120},
//                {TOBACCO, 20},
//                {TEA, 200},
//                {VANILLA, 70}
//        }).collect(Collectors.toMap(data -> (GoodsType) data[0], data -> (Integer) data[1]));
//    }
//
//    private static Map<GoodsType, Integer> guatemalaStartGoods() {
//        return Stream.of(new Object[][]{
//                {COFFEE, 240},
//                {COCOA, 240},
//                {SUGARCANE, 80},
//                {COTTON, 120},
//                {TOBACCO, 20},
//                {TEA, 200},
//                {VANILLA, 70}
//        }).collect(Collectors.toMap(data -> (GoodsType) data[0], data -> (Integer) data[1]));
//    }
//
//    private static Map<GoodsType, Integer> guatemalaOrder() {
//        return Stream.of(new Object[][]{
//                {COFFEE, 240},
//                {COCOA, 240},
//                {SUGARCANE, 80},
//                {COTTON, 120},
//                {TOBACCO, 20},
//                {TEA, 200},
//                {VANILLA, 70}
//        }).collect(Collectors.toMap(data -> (GoodsType) data[0], data -> (Integer) data[1]));
//    }
//
//    private static Map<GoodsType, Integer> puertoRicoStartGoods() {
//        return Stream.of(new Object[][]{
//                {COFFEE, 240},
//                {COCOA, 240},
//                {SUGARCANE, 80},
//                {COTTON, 120},
//                {TOBACCO, 20},
//                {TEA, 200},
//                {VANILLA, 70}
//        }).collect(Collectors.toMap(data -> (GoodsType) data[0], data -> (Integer) data[1]));
//    }
//
//    private static Map<GoodsType, Integer> puertoRicoOrder() {
//        return Stream.of(new Object[][]{
//                {COFFEE, 240},
//                {COCOA, 240},
//                {SUGARCANE, 80},
//                {COTTON, 120},
//                {TOBACCO, 20},
//                {TEA, 200},
//                {VANILLA, 70}
//        }).collect(Collectors.toMap(data -> (GoodsType) data[0], data -> (Integer) data[1]));
//    }

    @JsonValue
    public String getName() {
        return name;
    }
}
