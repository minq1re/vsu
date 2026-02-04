namespace ChessApp.Model
{
    public enum Color
    {
        Black,
        White,
    }

    public abstract class Figure
    {
        public Color Color { get; set; }
        public (int row, int col) Position { get; set; }

        readonly Dictionary<int, string> mapRev = new() {
        {0, "a"}, {1, "b"},
        {2, "c"}, {3, "d"},
        {4, "e"}, {5, "f"},
        {6, "g"}, {7, "h"}};

        public Figure(Color color, (int row, int col) position)
        {
            Color = color;
            Position = position;
        }

        public bool MakeMove(int row, int col) {
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
            int figureRow = Position.row + 1;
            string figureCol = mapRev[Position.col];
            return figureCol + " " + figureRow;
        }

    }
}
