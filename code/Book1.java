public class Book1 {    //Ä£ÐÍ²ã
    private int id;
    private String name;
    private String storehouse;
    private String assortment;
    private String place;
    private int price;
    private int stock;
    public Book1(){
        super();
    }

    public Book1(int id,String name,String storehouse,String assortment,String place,int price,int stock){
        super();
        this.id=id;
        this.name=name;
        this.storehouse=storehouse;
        this.assortment=assortment;
        this.place=place;
        this.price=price;
        this.stock=stock;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getStorehouse(){
        return storehouse;
    }
    public void setStorehouse(String storehouse){
        this.storehouse=storehouse;
    }

    public String getAssortment(){
        return assortment;
    }
    public void setPress(String assortment){
        this.assortment=assortment;
    }

    public String getPlace(){
        return place;
    }
    public void setPlace(String date){
        this.place=place;
    }

    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price=price;
    }

    public int getStock(){
        return stock;
    }
    public void setStock(int stock){
        this.stock=stock;
    }
}
