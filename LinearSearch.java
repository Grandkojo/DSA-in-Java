public class LinearSearch {

    public static int linearSearch(String[] receipt, String target){

        for (int i = 0; i < receipt.length; i++){
            if (receipt[i].equals(target))
                return i;
        }
        return -1;

    }
    public static void main(String[] args) {

        String[] receipt = new String[]{"Apples","Bananas","Avocados","Spinach","Broccoli","Carrots","Onions","Potatoes","Sweet potatoes","Bell peppers","Cucumbers","Tomatoes","Chicken breast","Ground beef","Eggs","Milk","Cheddar cheese","Greek yogurt","Butter","Bread","Whole grain pasta","Brown rice","Oats","Cereal","Olive oil","Salt","Black pepper","Garlic","Canned black beans","Canned diced tomatoes","Canned tuna","Peanut butter","Almonds","Coffee","Tea bags","Paper towels","Toilet paper","Laundry detergent","Dish soap","Toothpaste","Shampoo","Soap","Frozen peas","Frozen mixed berries","Ice cream","Crackers","Chips","Salsa","Orange juice","Water bottles"};

        System.out.println(linearSearch(receipt, "Avocados"));
    }
}
