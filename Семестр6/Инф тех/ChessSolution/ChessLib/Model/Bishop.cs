namespace ChessLib.Model
{
    public class Bishop : Figure
    {
        public Bishop(Color color, (int row, int col) position)
            : base(color, position)
        {
        }

        protected override bool IsMovePossible(int row, int col)
        {
            if (row < 0 || row > 7 || col < 0 || col > 7)
                return false;
            return System.Math.Abs(Position.row - row) == System.Math.Abs(Position.col - col);
        }
    }
}