package com.tictactoe.springboottictactieapi;

import com.tictactoe.springboottictactieapi.Classes.Play;
import com.tictactoe.springboottictactieapi.Classes.PossiblePlay;
import com.tictactoe.springboottictactieapi.Classes.RequestObject;
import com.tictactoe.springboottictactieapi.TrainBot.TicTacToe;
import org.slf4j.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
public class TicTacToeApiController {

    Logger logger= LoggerFactory.getLogger(TicTacToeApiController.class);
    @GetMapping("/train")
    public String Train() {
        logger.info("training started");
        for (int i = 0; i <= 20000; i++) {
            TicTacToe train = new TicTacToe();
            train.run();
        }
        logger.info("training ended");
        return "Tic Tac Toe";
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/process", method = RequestMethod.POST)
        public PossiblePlay GetBestMove(@RequestBody List<RequestObject> request) {
      //logger.info("Menace Tic-TAc-Toe started...");
        char[][] board = new char[3][3];
        for(int i = 0; i < request.size(); i++) {
            board[i][0] = request.get(i).left.charAt(0);
            board[i][1] = request.get(i).center.charAt(0);
            board[i][2] = request.get(i).right.charAt(0);
        }
        
        var play = new Play(board);
        return play.computerPlay('o', -1, 4, 0);
    }
}
