package model;
import java.awt.Color;
import java.util.Arrays;

public abstract class AShape {
    protected  int[][] element; // mảng 2 chiều để biểu diễn hình dạng
    protected Color color; // màu của khối
    protected int x; // vị trí của khối trên trục x và y
    protected int y;
    protected int size; // kích thước của khối

    public AShape() {

    }
    public AShape(int x, int y, int size) {
        this.x = 4;
        this.y = y;
        this.size = 30;
    }
    //kiểm tra bước tiếp theo khối có di chuyển ra ngoài biên dưới của bảng không không
    public boolean checkCollideBelow(int boardHeight) {
        return this.y + 1 + this.element.length > boardHeight;
        // y + 1 + chiều cao của khối > chiều cao của bảng không. Nếu lớn hơn trả về true.
    }
    // kiểm tra khối di chuyển có chạm vào khối nào có sẵn trong bảng không
    public boolean checkVerticalForMovement(Color[][] board){
        for (int i = 0; i < this.element.length; i++) {
            for (int j = 0; j < this.element[i].length; j++) {
                if (this.element[i][j] == 1 && (board[this.y + 1 + i][this.x + j] != null))
                    // nếu vị trí đó bằng 1 đã có 1 khối sẵn
                    // xác định vị trí tiếp theo của cột và hàng có khối nào chưa
                    return true;
            }
        }
        return false;
    }
    // di chuyển xuống
    public void moveDown() {
        this.y++;
    }
    // di chuyển theo phương ngang
    // qua phải int positionX là 1, qua trái int positionX -1
    public void moveHorizontal(int positionX, int boardWidth) {
        if (!(this.x + this.element[0].length + positionX > boardWidth)
                && !(this.x + positionX < 0)){
            //nếu chưa vượt qua khỏi chiều rộng của bảng thì được di chuyển
            this.x += positionX;
        }
    }

    public int[][] getElement() {
        return element;
    }

    public void setElements(int[][] elements) {
        this.element = elements;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "elements=" + Arrays.toString(element) +
                ", color=" + color +
                ", x=" + x +
                ", y=" + y +
                ", size=" + size +
                '}';
    }
}
