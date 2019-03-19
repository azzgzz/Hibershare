package ru.azzgzz.data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "Product")
@Table(name = "product")
public class Product extends Model {

    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id", referencedColumnName = "id")
    private ProductCategory productCategory;

    public Product() {
        super();
    }

    public Product(Long id) {
        super(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public String toString(){
        return super.getId() + " - " + title + " - " + description + " - ";
    }

    public Product(String title, BigDecimal price, String description, ProductCategory productCategory) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.productCategory = productCategory;
    }

    public Product(Long id, String title, BigDecimal price, String description, ProductCategory productCategory) {
        super(id);
        this.title = title;
        this.price = price;
        this.description = description;
        this.productCategory = productCategory;
    }
}
