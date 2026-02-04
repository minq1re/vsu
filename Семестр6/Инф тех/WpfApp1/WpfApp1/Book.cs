namespace WpfApp1
{
    public enum BookState
    {
        InStock,
        Issued,
        UnderRestoration
    }

    public class Book
    {
        public string LibraryCode { get; set; }
        public string Title { get; }
        public string Authors { get; }
        public int PageCount { get; }
        public string Theme { get; }
        public BookState State { get;  set; }

        public Book(string libraryCode, string title, string authors, int pageCount, string theme, BookState state)
        {
            LibraryCode = libraryCode;
            Title = title;
            Authors = authors;
            PageCount = pageCount;
            Theme = theme;
            State = state;
        }

        public void ChangeState(BookState newState)
        {
            State = newState;
        }

        public void ChangeLibraryCode(string newCode)
        {
            LibraryCode = newCode;
        }
    }
}
