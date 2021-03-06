import { Component, OnInit } from '@angular/core';
import { MenaceService } from './menace.service';
import { RequestObject } from './RequestObject';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  public gameState: Array<number | string> = [0, 1, 2, 3, 4, 5, 6, 7, 8];
  public winner: string = '';
  public playing: boolean = false;
  public computerFirst: boolean = false;
  public difficulty: "Easy" | "Normal" | "Hard" = "Normal";
  isLoading = false;

  constructor(private menaceService: MenaceService) { }

  ngOnInit() {
  }

  toggleGame(toggle: boolean) {
    if (toggle === this.playing) return;

    this.gameState = [0, 1, 2, 3, 4, 5, 6, 7, 8];
    // this.winner = undefined;

    if (toggle && this.computerFirst) {
      this.makeComputerMove();
    }

    this.playing = toggle;
  }

  makeComputerMove() {
    const symbols = {
      huPlayer: 'X',
      aiPlayer: 'O'
    };
    let board: RequestObject[] = [];
    board.push({
      left: typeof this.gameState[0] !== 'number' ? this.gameState[0].toString().toLowerCase() : ' ',
      center: typeof this.gameState[1] !== 'number' ? this.gameState[1].toString().toLowerCase() : ' ',
      right: typeof this.gameState[2] !== 'number' ? this.gameState[2].toString().toLowerCase() : ' ',
    });
    board.push({
      left: typeof this.gameState[3] !== 'number' ? this.gameState[3].toString().toLowerCase() : ' ',
      center: typeof this.gameState[4] !== 'number' ? this.gameState[4].toString().toLowerCase() : ' ',
      right: typeof this.gameState[5] !== 'number' ? this.gameState[5].toString().toLowerCase() : ' ',
    });
    board.push({
      left: typeof this.gameState[6] !== 'number' ? this.gameState[6].toString().toLowerCase() : ' ',
      center: typeof this.gameState[7] !== 'number' ? this.gameState[7].toString().toLowerCase() : ' ',
      right: typeof this.gameState[8] !== 'number' ? this.gameState[8].toString().toLowerCase() : ' ',
    });
    ``
    this.isLoading = true;
    this.menaceService.getComputerMove(board).subscribe((res) => {
      this.gameState[(res.row * 3) + res.col] = 'O';
      this.checkForWin();
      this.isLoading = false;
    });
  }

  checkForWin(): boolean {
    const winnerMapping = {
      huPlayer: "Human Wins!",
      aiPlayer: "Computer Wins!",
      draw: "It's a Draw!"
    };
    if ((this.gameState[0] == 'X' && this.gameState[1] == 'X' && this.gameState[2] == 'X') ||
      (this.gameState[3] == 'X' && this.gameState[4] == 'X' && this.gameState[5] == 'X') ||
      (this.gameState[6] == 'X' && this.gameState[7] == 'X' && this.gameState[8] == 'X') ||
      (this.gameState[0] == 'X' && this.gameState[3] == 'X' && this.gameState[6] == 'X') ||
      (this.gameState[1] == 'X' && this.gameState[4] == 'X' && this.gameState[7] == 'X') ||
      (this.gameState[2] == 'X' && this.gameState[5] == 'X' && this.gameState[8] == 'X') ||
      (this.gameState[0] == 'X' && this.gameState[4] == 'X' && this.gameState[8] == 'X') ||
      (this.gameState[2] == 'X' && this.gameState[4] == 'X' && this.gameState[6] == 'X')) {
      this.playing = false;
      this.winner = winnerMapping.huPlayer;
      return true;
    }

    if ((this.gameState[0] == 'O' && this.gameState[1] == 'O' && this.gameState[2] == 'O') ||
      (this.gameState[3] == 'O' && this.gameState[4] == 'O' && this.gameState[5] == 'O') ||
      (this.gameState[6] == 'O' && this.gameState[7] == 'O' && this.gameState[8] == 'O') ||
      (this.gameState[0] == 'O' && this.gameState[3] == 'O' && this.gameState[6] == 'O') ||
      (this.gameState[1] == 'O' && this.gameState[4] == 'O' && this.gameState[7] == 'O') ||
      (this.gameState[2] == 'O' && this.gameState[5] == 'O' && this.gameState[8] == 'O') ||
      (this.gameState[0] == 'O' && this.gameState[4] == 'O' && this.gameState[8] == 'O') ||
      (this.gameState[2] == 'O' && this.gameState[4] == 'O' && this.gameState[6] == 'O')) {
      this.playing = false;
      this.winner = winnerMapping.aiPlayer;
      return true;
    }


    if (typeof this.gameState[0] !== 'number' && typeof this.gameState[1] !== 'number' && typeof this.gameState[2] !== 'number'
      && typeof this.gameState[3] !== 'number' && typeof this.gameState[4] !== 'number' && typeof this.gameState[5] !== 'number'
      && typeof this.gameState[6] !== 'number' && typeof this.gameState[7] !== 'number' && typeof this.gameState[8] !== 'number') {
      this.playing = false;
      this.winner = winnerMapping.draw;
      return true;
    }

    return false
  }

  makeHumanMove(field: number) {
    if (!this.playing || typeof this.gameState[field] !== 'number') return;

    this.gameState[field] = 'X';
    if (!this.checkForWin()) {
      this.makeComputerMove();
    }
  }

}
