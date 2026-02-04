using ChessLib.Interfaces;
using System.Collections.Generic;

namespace ChessLib.Model
{
    public enum Color
    {
        Black,
        White
    }

    public abstract class Figure : IChessFigure
    {
        public Color Color { get; set; }
        public (int row, int col) Position { get; set; }
        
        private static readonly Dictionary<int, string> mapRev = new()
        {
            { 0, "a" }, { 1, "b" },
            { 2, "c" }, { 3, "d" },
            { 4, "e" }, { 5, "f" },
            { 6, "g" }, { 7, "h" }
        };

        protected Figure(Color color, (int row, int col) position)
        {
            Color = color;
            Position = position;
        }
        
        public bool MakeMove(int row, int col)
        {
            if (IsMovePossible(row, col))
            {
                Position = (row, col);
                return true;
            }
            return false;
        }
        
        protected abstract bool IsMovePossible(int row, int col);

        public override string ToString()
        {
            int displayRow = Position.row + 1;
            string displayCol = mapRev[Position.col];
            return $"{displayCol}{displayRow}";
        }
    }
}