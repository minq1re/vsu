using System.Windows;
using ChessApp.Model;
using Color = ChessApp.Model.Color;
using Figure = ChessApp.Model.Figure;

namespace ChessApp
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        readonly Dictionary<string, int> map = new() {
        {"a", 0}, {"b", 1},
        {"c", 2}, {"d", 3},
        {"e", 4}, {"f", 5},
        {"g", 6}, {"h", 7}};

        readonly Dictionary<int, string> mapRev = new() {
        {0, "a"}, {1, "b"},
        {2, "c"}, {3, "d"},
        {4, "e"}, {5, "f"},
        {6, "g"}, {7, "h"}};
        private Figure figure = new Queen(Color.White, (0, 0));

        public MainWindow()
        {
            InitializeComponent();
        }

        private void Fix(object sender, RoutedEventArgs e)
        {
            string pos = position.Text;
            string col = pos[..1].ToLower();
            string row = pos[1..];
            int colNumber = map.ContainsKey(col) ? map[col] : -1;
            int rowNumber = Convert.ToInt32(row) - 1;
            if (colNumber < 0 || colNumber > 7 || rowNumber < 0 || rowNumber > 7)
            {
                MessageBox.Show("Некорректная позиция!");
                return;
            }
            Color color = Color.White;
            if (rb4.IsChecked == true) {
                color = Color.White;
            }
            if (rb5.IsChecked == true)
            {
                color = Color.Black;
            }
            if (rb1.IsChecked == true) {
                figure = new Queen(color, (rowNumber, colNumber));
            }
            if (rb2.IsChecked == true)
            {
                figure = new Bishop(color, (rowNumber, colNumber));
            }
            if (rb3.IsChecked == true)
            {
                figure = new Rook(color, (rowNumber, colNumber));
            }
            result.Text = figure.ToString();
            string typeOfFigure = "";
            if (figure is Queen) typeOfFigure = "Ферзь";
            if (figure is Rook) typeOfFigure = "Ладья";
            if (figure is Bishop) typeOfFigure = "Слон";
            string colorOfFigure = "";
            if (figure.Color == Color.White) colorOfFigure = "Белый";
            if (figure.Color == Color.Black) colorOfFigure = "Черный";
            type.Text = colorOfFigure + " " + typeOfFigure;
        }
        private void MakeMove(object sender, RoutedEventArgs e)
        {
            string pos = positionTo.Text;
            string col = pos[..1].ToLower();
            string row = pos[1..];
            int colNumber = map.ContainsKey(col) ? map[col] : -1;
            int rowNumber = Convert.ToInt32(row) - 1;
            if (figure.MakeMove(rowNumber, colNumber))
            {
                result.Text = figure.ToString();
                string typeOfFigure = "";
                if (figure is Queen) typeOfFigure = "Ферзь";
                if (figure is Rook) typeOfFigure = "Ладья";
                if (figure is Bishop) typeOfFigure = "Слон";
                string colorOfFigure = "";
                if (figure.Color == Color.White) colorOfFigure = "Белый";
                if (figure.Color == Color.Black) colorOfFigure = "Черный";
                type.Text = colorOfFigure + " " + typeOfFigure;
            }
            else {
                MessageBox.Show("Ход невозможен");
            }
        }
    }
}