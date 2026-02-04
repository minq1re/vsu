namespace ChessLib.Interfaces
{
    public interface IChessFigure
    {
        bool MakeMove(int row, int col);
        string ToString();
    }
}