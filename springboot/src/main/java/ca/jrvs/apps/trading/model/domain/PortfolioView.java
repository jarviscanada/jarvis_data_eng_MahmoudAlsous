package ca.jrvs.apps.trading.model.domain;

import java.util.List;

public class PortfolioView {

    private List<Position> positions;
    private Account account;

    public PortfolioView(Account account, List<Position> positions) {
        this.positions = positions;
        this.account = account;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPosition(List<Position> positions) {
        this.positions = positions;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}