package mx.com.hexlink.es.lifeinbike.commondata.exceptions;



public class DatabaseExeption {
    // Class to throw Save data exception.
    public static class SaveDataExeption extends Exception{
        public SaveDataExeption(String message, Throwable ex){
            super(message, ex);
            ex.printStackTrace();
        }
    }

    // Class to throw retrive data exception.
    public static class RetriveDataExeption extends Exception{
        public RetriveDataExeption(String message, Throwable ex){
            super(message, ex);
            ex.printStackTrace();
        }
    }

    // Class to throw find data exception.
    public static class DeleteDataExeption extends Exception{
        public DeleteDataExeption(String message, Throwable ex){
            super(message, ex);
            ex.printStackTrace();
        }
    }
}
