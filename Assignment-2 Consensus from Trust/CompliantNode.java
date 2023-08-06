import java.util.Set;
import java.util.HashSet;

public class CompliantNode implements Node {

    // Initialize parameters
    public double p_graph;
    public double p_malicious;
    public double p_txDistribution;
    private int numRounds;
    private int currentRound;
    private Set<Transaction> pendingTransactions;
    private Set<Transaction> previousTransactions;
    private static Set<Transaction> acceptedTransactions;
    private boolean[] blacklistedNodes;
    private boolean[] followees;

    public CompliantNode(double p_graph, double p_malicious, double p_txDistribution, int numRounds) {
        this.p_graph = p_graph;
        this.p_malicious = p_malicious;
        this.p_txDistribution = p_txDistribution;
        this.numRounds = numRounds;
        this.pendingTransactions = new HashSet<>();
        this.currentRound = 0;
    }

    public void setFollowees(boolean[] followees) {
        this.followees = followees;
        blacklistedNodes = new boolean[followees.length];
    }

    public void setPendingTransaction(Set<Transaction> pendingTransactions) {
        if (acceptedTransactions == null) {
            acceptedTransactions = new HashSet<>(pendingTransactions);
        }
        // Add all pendingTransactions
        acceptedTransactions.addAll(pendingTransactions);
        this.pendingTransactions = acceptedTransactions;
    }

    public Set<Transaction> sendToFollowers() {
        Set<Transaction> broadcastTransactions = new HashSet<>();
        currentRound++;

        if (currentRound < numRounds) {
            return broadcastTransactions;
        }
        // Update pendingTransactions
        pendingTransactions = acceptedTransactions;
        broadcastTransactions = pendingTransactions;
        return broadcastTransactions;
    }

    public void receiveFromFollowees(Set<Candidate> candidates) {

        Set<Integer> receivedCandidates = new HashSet<>();

        for (Candidate candidate : candidates) {
            receivedCandidates.add(candidate.sender);
            for (int i = 0; i < followees.length; i++) {
                if (!blacklistedNodes[candidate.sender]) {
                    pendingTransactions.add(candidate.tx);
                }
                if (followees[i] && !receivedCandidates.contains(i)) {
                    blacklistedNodes[i] = true;
                }
            }
        }

    }
}