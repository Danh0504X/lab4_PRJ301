/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Product implements Serializable {
    private String id;                 // IDENTITY(1,1)
    private String name;                // NVARCHAR(255) NOT NULL
    private String price;           // DECIMAL(10,2) NOT NULL
    private String description;         // NVARCHAR(500) NULL
    private String stock;              // INT NOT NULL DEFAULT 0
    private LocalDateTime importDate;   // DATETIME DEFAULT GETDATE()
     private boolean  Status;

    public Product() {}

    // Constructor đầy đủ (trừ id vì DB tự tăng)
    public Product(String name, String price, String description, String stock, LocalDateTime importDate, boolean Status) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.importDate = importDate;
        this.Status = Status;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public LocalDateTime getImportDate() {
        return importDate;
    }

    public void setImportDate(LocalDateTime importDate) {
        this.importDate = importDate;
    }
   

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", importDate=" + importDate +
                '}';
    }

    // equals/hashCode theo id (nếu có)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product that = (Product) o;
        return id != null && id.equals(that.id);
    }
    @Override
    public int hashCode() { return id != null ? id.hashCode() : 0; }
}
