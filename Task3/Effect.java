// interface kõikidele efektidele (договор со всеми классами, на то, чтобы у них были эти методы)
interface Effect {

    void onHit(Dude effectTarget);     // kui rünnak tabab
    void onTurnStart(Dude effectTarget); // käigu alguses
    void onTurnEnd(Dude effectTarget);   // käigu lõpus

    int requiredActionPoints(); // mitu poinst on vaja
    boolean isExpired(); // kas efekt on läbi
}