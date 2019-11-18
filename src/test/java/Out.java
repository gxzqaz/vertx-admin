import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Out {
  public static void main(String[] args) {
    List<Integer> integers = Arrays.asList(1, 55, 88, 2, 5, 9, 18, 6, 99);
    System.out.println(integers.stream().filter(a -> a > 10).collect(Collectors.toList()));
  }
}
