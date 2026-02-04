namespace ChessApp.Model
{
    public class Queen : Figure
    {
        public Queen(Color color, (int row, int col) position) : base(color, position)
        {
        }

        protected override bool IsMovePossible(int row, int col)
        {
            if (row < 0 || row > 7 || col < 0 || col > 7) return false;
            if (Position.row != row && Position.col != col && ((Position.row + Position.col) != (row + col)) && ((Position.row - Position.col) != (row - col)))
            {
                return false;
            }
            return true;
        }
    }
}
