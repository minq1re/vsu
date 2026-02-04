namespace ChessLib.Model
{
    public class Rook : Figure
    {
        public Rook(Color color, (int row, int col) position)
            : base(color, position)
        {
        }

        protected override bool IsMovePossible(int row, int col)
        {
            if (row < 0 || row > 7 || col < 0 || col > 7)
                return false;
            
            return Position.row == row || Position.col == col;
        }
    }
}