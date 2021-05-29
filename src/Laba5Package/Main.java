package Laba5Package;

import Laba5Package.Controllers.Controller;
import Laba5Package.Models.Graph;
import Laba5Package.Views.View;

public class Main {
    public static void main(String[] args) {
        new Controller(new View(), new Graph());
    }
}