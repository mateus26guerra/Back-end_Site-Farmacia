package projeto_base_de_telas_e_login.domain.model.product;

public class Product {

    private Integer id;
    private String name;
    private Double price;
    private String imagemUrl;

    public Product() {
    }

    public Product(Integer id, String name, Double price, String imagemUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imagemUrl = imagemUrl;
    }

    public Product(String name, Double price, String imagemUrl) {
        this(null, name, price, imagemUrl);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }
}
