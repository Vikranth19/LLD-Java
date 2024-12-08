package com.selfLearning.LLD.LLDTicTacToe.Model;

import lombok.Data;

@Data
public class Player {

    public String name;
    public PlayingPiece playingPiece;

    public Player(String name, PlayingPiece playingPiece) {
        this.name = name;
        this.playingPiece = playingPiece;
    }
}
