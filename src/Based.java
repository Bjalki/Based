/* Generated By:JavaCC: Do not edit this line. Based.java */
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Based implements BasedConstants {
  public static Scanner in;

  public static void main(String args[]) throws ParseException
  {
    try {
      Based parser = null;
      in = new Scanner(System.in);
      if (args.length > 0)
      parser = new Based(new FileInputStream(args[0]));
      else
      {
        System.out.print("Program filename: ");
        parser = new Based(new FileInputStream(in.next()));
       }
      Program p = parser.program();
      p.eval(new Env());
      //p.print();
    }
    catch (FileNotFoundException e)
    {
      System.out.println(e.getMessage());
    }
    catch (RuntimeException e)
    {
      System.out.println(e.getMessage());
    }
  }

  static final public Program program() throws ParseException {
  Program p = new Program(); Line l = null;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DEFINE:
      case NOT:
      case INTEGER:
      case TRUE:
      case FALSE:
      case READ:
      case WRITE:
      case NEWLINE:
      case CONTAINS:
      case ADD:
      case SWAP:
      case SET:
      case IDENTIFIER:
      case 36:
      case 40:
      case 41:
      case 42:
      case 44:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DEFINE:
        l = reg();
              p.lineList.add(l);
        break;
      case NOT:
      case INTEGER:
      case TRUE:
      case FALSE:
      case READ:
      case WRITE:
      case NEWLINE:
      case CONTAINS:
      case ADD:
      case SWAP:
      case SET:
      case IDENTIFIER:
      case 36:
      case 40:
      case 41:
      case 42:
      case 44:
        l = exp();
                                               p.lineList.add(l);
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return p;}
    throw new Error("Missing return statement in function");
  }

  static final public Reg reg() throws ParseException {
  Reg r = new Reg(); Exp e = null; Label l = null;
    jj_consume_token(DEFINE);
    if (jj_2_1(2147483647)) {
      l = label();
                                             r.label = l;
    } else {
      ;
    }
    jj_consume_token(35);
    e = exp();
            r.exp = e;
    {if (true) return r;}
    throw new Error("Missing return statement in function");
  }

  static final public Exp exp() throws ParseException {
  Exp e = null;
    if (jj_2_2(2147483647)) {
      e = lambda();
    } else if (jj_2_3(2147483647)) {
      e = derivedExp();
    } else if (jj_2_4(2147483647)) {
      e = call();
    } else if (jj_2_5(2147483647)) {
      e = assign();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INTEGER:
      case TRUE:
      case FALSE:
      case IDENTIFIER:
      case 36:
      case 40:
      case 42:
      case 44:
        e = singular();
        break;
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  static final public AssignExp assign() throws ParseException {
  AssignExp a = new AssignExp(); Exp e = null;
    jj_consume_token(36);
    e = exp();
                      a.data = e;
    jj_consume_token(ASSIGN);
    e = exp();
                                                         a.location = e;
    jj_consume_token(37);
          {if (true) return a;}
    throw new Error("Missing return statement in function");
  }

  static final public Cond ternary() throws ParseException {
  Cond c = null; Exp e1 = null; Exp e2 = null; Exp e3 = null;
    jj_consume_token(36);
    e1 = exp();
    jj_consume_token(38);
    e2 = exp();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 39:
      jj_consume_token(39);
      e3 = exp();
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
    jj_consume_token(37);
    c = new Cond(e1, e2, e3); {if (true) return c;}
    throw new Error("Missing return statement in function");
  }

  static final public Exp singular() throws ParseException {
  Exp e = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
      e = label();
      break;
    case INTEGER:
    case TRUE:
    case FALSE:
      e = literal();
      break;
    case 44:
      e = access();
      break;
    case 36:
      e = ternary();
      break;
    case 42:
      e = block();
      break;
    case 40:
      e = loop();
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  static final public LoopExp loop() throws ParseException {
  LoopExp le = new LoopExp(); Exp e = null; LambdaExp func = null;
    jj_consume_token(40);
    e = exp();
                le.exp1 = e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 35:
      jj_consume_token(35);
      e = exp();
                                              le.exp2 = e;
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
    jj_consume_token(39);
    func = lambda();
                                                                                   le.func = func;
    jj_consume_token(40);
    {if (true) return le;}
    throw new Error("Missing return statement in function");
  }

  static final public Label label() throws ParseException {
  Label l = null; String id = null;
    jj_consume_token(IDENTIFIER);
                   id = token.image; l = new Label(id);
    {if (true) return l;}
    throw new Error("Missing return statement in function");
  }

  static final public Literal literal() throws ParseException {
  Literal l = null; String s = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER:
      jj_consume_token(INTEGER);
                  s = token.image; l = new Int(s);
      break;
    case TRUE:
    case FALSE:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TRUE:
        jj_consume_token(TRUE);
        break;
      case FALSE:
        jj_consume_token(FALSE);
        break;
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
                             s = token.image; l = new Bool(s);
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return l;}
    throw new Error("Missing return statement in function");
  }

  static final public Exp derivedExp() throws ParseException {
  Exp exp1 = null; Exp exp2 = null; Exp exp3 = null; Token t = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case READ:
      jj_consume_token(READ);
      jj_consume_token(41);
                     exp1 = new ReadExp(in);
      break;
    case WRITE:
      jj_consume_token(WRITE);
      jj_consume_token(36);
      exp1 = exp();
      jj_consume_token(37);
                                    exp1 = new WriteExp(exp1);
      break;
    case NEWLINE:
      jj_consume_token(NEWLINE);
      jj_consume_token(41);
                        exp1 = new NewlineExp();
      break;
    case ADD:
      jj_consume_token(ADD);
      jj_consume_token(36);
      exp1 = exp();
      jj_consume_token(35);
      exp2 = exp();
      jj_consume_token(37);
                                                 exp1 = new AddExp(exp1, exp2);
      break;
    case CONTAINS:
      jj_consume_token(CONTAINS);
      jj_consume_token(36);
      exp1 = exp();
      jj_consume_token(35);
      exp2 = exp();
      jj_consume_token(37);
                                                      exp1 = new ContainsExp(exp1, exp2);
      break;
    case SWAP:
      jj_consume_token(SWAP);
      jj_consume_token(36);
      exp1 = exp();
      jj_consume_token(35);
      exp2 = exp();
      jj_consume_token(35);
      exp3 = exp();
      jj_consume_token(37);
                                                                 exp1 = new SwapExp(exp1, exp2, exp3);
      break;
    case SET:
      jj_consume_token(SET);
      jj_consume_token(36);
      exp1 = exp();
      jj_consume_token(35);
      exp2 = exp();
      jj_consume_token(35);
      exp3 = exp();
      jj_consume_token(37);
                                                                exp1 = new SetExp(exp1, exp2, exp3);
      break;
    case NOT:
      jj_consume_token(NOT);
      jj_consume_token(36);
      exp1 = exp();
      jj_consume_token(37);
                                  exp1 = new NotExp(exp1);
      break;
    case 36:
      jj_consume_token(36);
      exp1 = exp();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
      case MULTIPLY:
      case DIVIDE:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
          jj_consume_token(PLUS);
          break;
        case MINUS:
          jj_consume_token(MINUS);
          break;
        case MULTIPLY:
          jj_consume_token(MULTIPLY);
          break;
        case DIVIDE:
          jj_consume_token(DIVIDE);
          break;
        default:
          jj_la1[8] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
                                                                        t = token;
        exp2 = exp();
                                                                                                  exp1 = new ArithExp(t.image, exp1, exp2);
        break;
      case LESS:
      case GREATER:
      case LEQ:
      case GEQ:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case GREATER:
          jj_consume_token(GREATER);
          break;
        case LESS:
          jj_consume_token(LESS);
          break;
        case GEQ:
          jj_consume_token(GEQ);
          break;
        case LEQ:
          jj_consume_token(LEQ);
          break;
        default:
          jj_la1[9] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
                                                          t = token;
        exp2 = exp();
                                                                                    exp1 = new CompareExp(t.image, exp1, exp2);
        break;
      case EQUAL:
        jj_consume_token(EQUAL);
        exp2 = exp();
                                  exp1 = new EqualExp(exp1, exp2);
        break;
      case AND:
      case OR:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case AND:
          jj_consume_token(AND);
          break;
        case OR:
          jj_consume_token(OR);
          break;
        default:
          jj_la1[10] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
                                t = token;
        exp2 = exp();
                                                          exp1 = new BoolOpExp(t.image, exp1, exp2);
        break;
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(37);
      break;
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return exp1;}
    throw new Error("Missing return statement in function");
  }

  static final public Call call() throws ParseException {
  Call c = new Call(); Exp e = null;
    e = singular();
                 c.head = e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 36:
      jj_consume_token(36);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NOT:
      case INTEGER:
      case TRUE:
      case FALSE:
      case READ:
      case WRITE:
      case NEWLINE:
      case CONTAINS:
      case ADD:
      case SWAP:
      case SET:
      case IDENTIFIER:
      case 36:
      case 40:
      case 41:
      case 42:
      case 44:
        e = exp();
                                                c.params.add(e);
        break;
      default:
        jj_la1[13] = jj_gen;
        ;
      }
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 35:
          ;
          break;
        default:
          jj_la1[14] = jj_gen;
          break label_2;
        }
        jj_consume_token(35);
        e = exp();
                                                                                    c.params.add(e);
      }
      jj_consume_token(37);
      break;
    case 41:
      jj_consume_token(41);
      break;
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return c;}
    throw new Error("Missing return statement in function");
  }

  static final public Block block() throws ParseException {
  Block b = new Block(""); Line l = null;
    jj_consume_token(42);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DEFINE:
      case NOT:
      case INTEGER:
      case TRUE:
      case FALSE:
      case READ:
      case WRITE:
      case NEWLINE:
      case CONTAINS:
      case ADD:
      case SWAP:
      case SET:
      case IDENTIFIER:
      case 36:
      case 40:
      case 41:
      case 42:
      case 44:
        ;
        break;
      default:
        jj_la1[16] = jj_gen;
        break label_3;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DEFINE:
        l = reg();
        break;
      case NOT:
      case INTEGER:
      case TRUE:
      case FALSE:
      case READ:
      case WRITE:
      case NEWLINE:
      case CONTAINS:
      case ADD:
      case SWAP:
      case SET:
      case IDENTIFIER:
      case 36:
      case 40:
      case 41:
      case 42:
      case 44:
        l = exp();
        break;
      default:
        jj_la1[17] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
                         b.lineList.add(l);
    }
    jj_consume_token(43);
    {if (true) return b;}
    throw new Error("Missing return statement in function");
  }

  static final public Reg innerReg() throws ParseException {
  Reg r = new Reg(); Exp e = null; Label l = null;
    l = label();
    jj_consume_token(DEFINE);
                         r.label = l;
    e = exp();
            r.exp = e;
    {if (true) return r;}
    throw new Error("Missing return statement in function");
  }

  static final public Access access() throws ParseException {
  Access a = null; Exp e1 = null; Exp e2 = null; Access next = null;
    jj_consume_token(44);
    e1 = exp();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 35:
      jj_consume_token(35);
      e2 = exp();
      break;
    default:
      jj_la1[18] = jj_gen;
      ;
    }
    jj_consume_token(45);
                                          if (e2 == null) a = new ExpAccess(e1); else a = new LocAccess(e1, e2);
    if (jj_2_6(2147483647)) {
      next = access();
                                                                                                                                                         a.next = next;
    } else {
      ;
    }
    {if (true) return a;}
    throw new Error("Missing return statement in function");
  }

  static final public LambdaExp lambda() throws ParseException {
  LambdaExp lam = new LambdaExp(); Exp e = null; Label lab = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 36:
      jj_consume_token(36);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IDENTIFIER:
        lab = label();
                       lam.labels.add(lab);
        break;
      default:
        jj_la1[19] = jj_gen;
        ;
      }
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 35:
          ;
          break;
        default:
          jj_la1[20] = jj_gen;
          break label_4;
        }
        jj_consume_token(35);
        lab = label();
                                                                   lam.labels.add(lab);
      }
      jj_consume_token(37);
      jj_consume_token(LAMBDA);
      e = exp();
                                                                                                                     lam.exp = e;
      break;
    case 41:
      jj_consume_token(41);
      jj_consume_token(LAMBDA);
      e = exp();
                              lam.exp = e;
      break;
    default:
      jj_la1[21] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return lam;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  static private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  static private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  static private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  static private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  static private boolean jj_3R_40() {
    if (jj_scan_token(35)) return true;
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_51() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(23)) {
    jj_scanpos = xsp;
    if (jj_scan_token(24)) return true;
    }
    return false;
  }

  static private boolean jj_3R_50() {
    if (jj_scan_token(INTEGER)) return true;
    return false;
  }

  static private boolean jj_3R_46() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_50()) {
    jj_scanpos = xsp;
    if (jj_3R_51()) return true;
    }
    return false;
  }

  static private boolean jj_3R_5() {
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  static private boolean jj_3_6() {
    if (jj_3R_10()) return true;
    return false;
  }

  static private boolean jj_3R_52() {
    if (jj_scan_token(39)) return true;
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_49() {
    if (jj_scan_token(40)) return true;
    if (jj_3R_24()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_54()) jj_scanpos = xsp;
    if (jj_scan_token(39)) return true;
    if (jj_3R_6()) return true;
    if (jj_scan_token(40)) return true;
    return false;
  }

  static private boolean jj_3R_26() {
    if (jj_3R_10()) return true;
    return false;
  }

  static private boolean jj_3R_25() {
    if (jj_scan_token(35)) return true;
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_38() {
    if (jj_3R_49()) return true;
    return false;
  }

  static private boolean jj_3R_37() {
    if (jj_3R_48()) return true;
    return false;
  }

  static private boolean jj_3R_36() {
    if (jj_3R_47()) return true;
    return false;
  }

  static private boolean jj_3R_27() {
    if (jj_3R_5()) return true;
    return false;
  }

  static private boolean jj_3R_35() {
    if (jj_3R_10()) return true;
    return false;
  }

  static private boolean jj_3R_34() {
    if (jj_3R_46()) return true;
    return false;
  }

  static private boolean jj_3R_39() {
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_33() {
    if (jj_3R_5()) return true;
    return false;
  }

  static private boolean jj_3R_12() {
    if (jj_scan_token(41)) return true;
    if (jj_scan_token(LAMBDA)) return true;
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_11() {
    if (jj_scan_token(36)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_27()) jj_scanpos = xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_28()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(37)) return true;
    if (jj_scan_token(LAMBDA)) return true;
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_22() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_33()) {
    jj_scanpos = xsp;
    if (jj_3R_34()) {
    jj_scanpos = xsp;
    if (jj_3R_35()) {
    jj_scanpos = xsp;
    if (jj_3R_36()) {
    jj_scanpos = xsp;
    if (jj_3R_37()) {
    jj_scanpos = xsp;
    if (jj_3R_38()) return true;
    }
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_6() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_11()) {
    jj_scanpos = xsp;
    if (jj_3R_12()) return true;
    }
    return false;
  }

  static private boolean jj_3R_23() {
    if (jj_scan_token(36)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_39()) jj_scanpos = xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_40()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(37)) return true;
    return false;
  }

  static private boolean jj_3R_47() {
    if (jj_scan_token(36)) return true;
    if (jj_3R_24()) return true;
    if (jj_scan_token(38)) return true;
    if (jj_3R_24()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_52()) jj_scanpos = xsp;
    if (jj_scan_token(37)) return true;
    return false;
  }

  static private boolean jj_3R_9() {
    if (jj_scan_token(36)) return true;
    if (jj_3R_24()) return true;
    if (jj_scan_token(ASSIGN)) return true;
    if (jj_3R_24()) return true;
    if (jj_scan_token(37)) return true;
    return false;
  }

  static private boolean jj_3R_10() {
    if (jj_scan_token(44)) return true;
    if (jj_3R_24()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_25()) jj_scanpos = xsp;
    if (jj_scan_token(45)) return true;
    xsp = jj_scanpos;
    if (jj_3R_26()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3_5() {
    if (jj_3R_9()) return true;
    return false;
  }

  static private boolean jj_3_4() {
    if (jj_3R_8()) return true;
    return false;
  }

  static private boolean jj_3R_56() {
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3_1() {
    if (jj_3R_5()) return true;
    return false;
  }

  static private boolean jj_3_3() {
    if (jj_3R_7()) return true;
    return false;
  }

  static private boolean jj_3_2() {
    if (jj_3R_6()) return true;
    return false;
  }

  static private boolean jj_3R_45() {
    if (jj_3R_22()) return true;
    return false;
  }

  static private boolean jj_3R_44() {
    if (jj_3R_9()) return true;
    return false;
  }

  static private boolean jj_3R_43() {
    if (jj_3R_8()) return true;
    return false;
  }

  static private boolean jj_3R_58() {
    if (jj_3R_5()) return true;
    return false;
  }

  static private boolean jj_3R_55() {
    if (jj_3R_57()) return true;
    return false;
  }

  static private boolean jj_3R_42() {
    if (jj_3R_7()) return true;
    return false;
  }

  static private boolean jj_3R_53() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_55()) {
    jj_scanpos = xsp;
    if (jj_3R_56()) return true;
    }
    return false;
  }

  static private boolean jj_3R_41() {
    if (jj_3R_6()) return true;
    return false;
  }

  static private boolean jj_3R_48() {
    if (jj_scan_token(42)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_53()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(43)) return true;
    return false;
  }

  static private boolean jj_3R_24() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_41()) {
    jj_scanpos = xsp;
    if (jj_3R_42()) {
    jj_scanpos = xsp;
    if (jj_3R_43()) {
    jj_scanpos = xsp;
    if (jj_3R_44()) {
    jj_scanpos = xsp;
    if (jj_3R_45()) return true;
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_29() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(5)) {
    jj_scanpos = xsp;
    if (jj_scan_token(6)) {
    jj_scanpos = xsp;
    if (jj_scan_token(7)) {
    jj_scanpos = xsp;
    if (jj_scan_token(8)) return true;
    }
    }
    }
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_8() {
    if (jj_3R_22()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_23()) {
    jj_scanpos = xsp;
    if (jj_scan_token(41)) return true;
    }
    return false;
  }

  static private boolean jj_3R_57() {
    if (jj_scan_token(DEFINE)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_58()) jj_scanpos = xsp;
    if (jj_scan_token(35)) return true;
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_32() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(18)) {
    jj_scanpos = xsp;
    if (jj_scan_token(19)) return true;
    }
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_31() {
    if (jj_scan_token(EQUAL)) return true;
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_30() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(15)) {
    jj_scanpos = xsp;
    if (jj_scan_token(14)) {
    jj_scanpos = xsp;
    if (jj_scan_token(17)) {
    jj_scanpos = xsp;
    if (jj_scan_token(16)) return true;
    }
    }
    }
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_21() {
    if (jj_scan_token(36)) return true;
    if (jj_3R_24()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_29()) {
    jj_scanpos = xsp;
    if (jj_3R_30()) {
    jj_scanpos = xsp;
    if (jj_3R_31()) {
    jj_scanpos = xsp;
    if (jj_3R_32()) return true;
    }
    }
    }
    if (jj_scan_token(37)) return true;
    return false;
  }

  static private boolean jj_3R_20() {
    if (jj_scan_token(NOT)) return true;
    if (jj_scan_token(36)) return true;
    if (jj_3R_24()) return true;
    if (jj_scan_token(37)) return true;
    return false;
  }

  static private boolean jj_3R_19() {
    if (jj_scan_token(SET)) return true;
    if (jj_scan_token(36)) return true;
    if (jj_3R_24()) return true;
    if (jj_scan_token(35)) return true;
    if (jj_3R_24()) return true;
    if (jj_scan_token(35)) return true;
    if (jj_3R_24()) return true;
    if (jj_scan_token(37)) return true;
    return false;
  }

  static private boolean jj_3R_18() {
    if (jj_scan_token(SWAP)) return true;
    if (jj_scan_token(36)) return true;
    if (jj_3R_24()) return true;
    if (jj_scan_token(35)) return true;
    if (jj_3R_24()) return true;
    if (jj_scan_token(35)) return true;
    if (jj_3R_24()) return true;
    if (jj_scan_token(37)) return true;
    return false;
  }

  static private boolean jj_3R_28() {
    if (jj_scan_token(35)) return true;
    if (jj_3R_5()) return true;
    return false;
  }

  static private boolean jj_3R_17() {
    if (jj_scan_token(CONTAINS)) return true;
    if (jj_scan_token(36)) return true;
    if (jj_3R_24()) return true;
    if (jj_scan_token(35)) return true;
    if (jj_3R_24()) return true;
    if (jj_scan_token(37)) return true;
    return false;
  }

  static private boolean jj_3R_54() {
    if (jj_scan_token(35)) return true;
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_16() {
    if (jj_scan_token(ADD)) return true;
    if (jj_scan_token(36)) return true;
    if (jj_3R_24()) return true;
    if (jj_scan_token(35)) return true;
    if (jj_3R_24()) return true;
    if (jj_scan_token(37)) return true;
    return false;
  }

  static private boolean jj_3R_15() {
    if (jj_scan_token(NEWLINE)) return true;
    if (jj_scan_token(41)) return true;
    return false;
  }

  static private boolean jj_3R_14() {
    if (jj_scan_token(WRITE)) return true;
    if (jj_scan_token(36)) return true;
    if (jj_3R_24()) return true;
    if (jj_scan_token(37)) return true;
    return false;
  }

  static private boolean jj_3R_13() {
    if (jj_scan_token(READ)) return true;
    if (jj_scan_token(41)) return true;
    return false;
  }

  static private boolean jj_3R_7() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_13()) {
    jj_scanpos = xsp;
    if (jj_3R_14()) {
    jj_scanpos = xsp;
    if (jj_3R_15()) {
    jj_scanpos = xsp;
    if (jj_3R_16()) {
    jj_scanpos = xsp;
    if (jj_3R_17()) {
    jj_scanpos = xsp;
    if (jj_3R_18()) {
    jj_scanpos = xsp;
    if (jj_3R_19()) {
    jj_scanpos = xsp;
    if (jj_3R_20()) {
    jj_scanpos = xsp;
    if (jj_3R_21()) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public BasedTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[22];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0xffb00200,0xffb00200,0x1a00000,0x0,0x1a00000,0x0,0x1800000,0x1a00000,0x1e0,0x3c000,0xc0000,0xfe1e0,0xfe100000,0xffb00000,0x0,0x0,0xffb00200,0xffb00200,0x0,0x0,0x0,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x1711,0x1711,0x1511,0x80,0x1511,0x8,0x0,0x0,0x0,0x0,0x0,0x0,0x10,0x1711,0x8,0x210,0x1711,0x1711,0x8,0x1,0x8,0x210,};
   }
  static final private JJCalls[] jj_2_rtns = new JJCalls[6];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Based(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Based(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new BasedTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Based(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new BasedTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Based(BasedTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(BasedTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[46];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 22; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 46; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 6; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}