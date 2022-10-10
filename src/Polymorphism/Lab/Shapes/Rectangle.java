package Polymorphism.Lab.Shapes;

public class Rectangle extends Shape {
    private Double height;
    private Double width;

    public Rectangle(Double height, Double width) {
        setHeight(height);
        setWidth(width);
        setPerimeter(this.calculatePerimeter());
        setArea(this.calculateArea());
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    @Override
    protected Double calculatePerimeter() {
        return 2 * height + 2 * width;
    }

    @Override
    protected Double calculateArea() {
        return height * width;
    }
}
