package dev.flamenbaum.constructor.dependencyInjection.game.internal;

interface InputProvider {
    BoardLocation provideNextMove(Board board);
}
