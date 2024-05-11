import controller.GameController;
import model.Board;
import model.builder.board.BoardBuilder;


public class Main {
    public static void main(String[] args) {
//        Board model = new BoardBuilder()
//                .width(10)
//                .height(20)
//                .delayTime(600)
//                .build();
        Board model = new BoardBuilder().build2();
        new GameController(model);
    }
}
