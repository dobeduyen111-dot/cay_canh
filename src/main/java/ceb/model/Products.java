package ceb.model;

import java.util.Date;

public class Products {
    private Integer ProductId;
    private Integer CategoryId;
    private String ProductName;
    private String Description;
    private String CareGuide;
    private double Price;
    private int Stock;
    private String Image;
    private boolean IsActive;
    private Date CreatedAt;

    public Products() {}

    public Products(Integer productId, Integer categoryId, String productName, String description,
                    String careGuide, double price, int stock, String image,
                    boolean isActive, Date createdAt) {
        ProductId = productId;
        CategoryId = categoryId;
        ProductName = productName;
        Description = description;
        CareGuide = careGuide;
        Price = price;
        Stock = stock;
        Image = image;
        IsActive = isActive;
        CreatedAt = createdAt;
    }

    public Integer getProductId() { return ProductId; }
    public void setProductId(Integer productId) { ProductId = productId; }

    public Integer getCategoryId() { return CategoryId; }
    public void setCategoryId(Integer categoryId) { CategoryId = categoryId; }

    public String getProductName() { return ProductName; }
    public void setProductName(String productName) { ProductName = productName; }

    public String getDescription() { return Description; }
    public void setDescription(String description) { Description = description; }

    public String getCareGuide() { return CareGuide; }
    public void setCareGuide(String careGuide) { CareGuide = careGuide; }

    public double getPrice() { return Price; }
    public void setPrice(double price) { Price = price; }

    public int getStock() { return Stock; }
    public void setStock(int stock) { Stock = stock; }

    public String getImage() { return Image; }
    public void setImage(String image) { Image = image; }

    public boolean isActive() { return IsActive; }
    public void setisActive(boolean active) { IsActive = active; }

    public Date getCreatedAt() { return CreatedAt; }
    public void setCreatedAt(Date createdAt) { CreatedAt = createdAt; }


}
