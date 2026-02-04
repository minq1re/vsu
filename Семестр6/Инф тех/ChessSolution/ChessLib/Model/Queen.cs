namespace ChessLib.Model
{
    public class Queen : Figure
    {
        public Queen(Color color, (int row, int col) position)
            : base(color, position)
        {
        }

        protected override bool IsMovePossible(int row, int col)
        {
            if (row < 0 || row > 7 || col < 0 || col > 7)
                return false;
            
            bool straight = Position.row == row || Position.col == col;
            bool diagonal = System.Math.Abs(Position.row - row) == System.Math.Abs(Position.col - col);
            return straight || diagonal;
        }
    }
}