package com.xeiam.xchange.cryptsy.service.polling;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;

import si.mazi.rescu.RestProxyFactory;

import com.xeiam.xchange.ExchangeException;
import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.NotAvailableFromExchangeException;
import com.xeiam.xchange.NotYetImplementedForExchangeException;
import com.xeiam.xchange.cryptsy.Cryptsy;
import com.xeiam.xchange.cryptsy.CryptsyUtils;
import com.xeiam.xchange.cryptsy.dto.cryptsy.marketdata.CryptsyDepthResult;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.ExchangeInfo;
import com.xeiam.xchange.dto.Order.OrderType;
import com.xeiam.xchange.dto.marketdata.OrderBook;
import com.xeiam.xchange.dto.marketdata.Ticker;
import com.xeiam.xchange.dto.marketdata.Trades;
import com.xeiam.xchange.dto.trade.LimitOrder;
import com.xeiam.xchange.service.polling.BasePollingExchangeService;
import com.xeiam.xchange.service.polling.PollingMarketDataService;
import com.xeiam.xchange.utils.Assert;

public class CryptsyMarketDataService extends BasePollingExchangeService implements PollingMarketDataService {

	private static final long PARTIAL_ORDERBOOK_SIZE = 200L;
	private final Cryptsy cryptsy;

	public CryptsyMarketDataService(ExchangeSpecification exchangeSpecification) {

		super(exchangeSpecification);
		Assert.notNull(exchangeSpecification.getSslUri(), "Exchange specification URI cannot be null");
		cryptsy = RestProxyFactory.createProxy(Cryptsy.class, exchangeSpecification.getSslUri());
	}

	@Override
	public List<CurrencyPair> getExchangeSymbols() {
		ArrayList<CurrencyPair> list = new ArrayList<CurrencyPair>();
		list.add(CurrencyPair.BTC_EUR);
		return list;
		// KrakenAssetPairsResult krakenAssetPairs = null;
		// try {
		// krakenAssetPairs = theRock.getAssetPairs();
		// } catch (IOException e) {
		// throw new
		// ExchangeException("Network error fetching exchange symbols!!!");
		// }
		// if (krakenAssetPairs.getError().length > 0) {
		// throw new ExchangeException(krakenAssetPairs.getError().toString());
		// }
		// return
		// KrakenAdapters.adaptCurrencyPairs(krakenAssetPairs.getResult().keySet());
	}

	@Override
	public Ticker getTicker(String tradableIdentifier, String currency) throws IOException {
		throw new NotAvailableFromExchangeException();
	}


	public OrderBook getPartialOrderBook(String tradableIdentifier, String currency) throws IOException {

		return getOrderBook(tradableIdentifier, currency, PARTIAL_ORDERBOOK_SIZE);
	}


	public OrderBook getFullOrderBook(String tradableIdentifier, String currency) throws IOException {

		return getOrderBook(tradableIdentifier, currency);
	}
	
	@Override
	public OrderBook getOrderBook(String tradableIdentifier, String currency, Object... args) throws ExchangeException, NotAvailableFromExchangeException,	NotYetImplementedForExchangeException, IOException {
	
		verify(tradableIdentifier, currency);
		CryptsyDepthResult cryptsyDeptResult = cryptsy.getDepth(CryptsyUtils.getTradeId(tradableIdentifier, currency));
		if(!cryptsyDeptResult.isSuccess()){
			throw new ExchangeException(cryptsyDeptResult.getError());
		}

//		String krakenCurrencyPair = KrakenUtils.createKrakenCurrencyPair(tradableIdentifier, currency);
//		KrakenDepthResult krakenDepthReturn = theRock.getDepth(krakenCurrencyPair, count);
//		if (krakenDepthReturn.getError().length > 0) {
//			throw new ExchangeException(Arrays.toString(krakenDepthReturn.getError()));
//		}
//		KrakenDepth krakenDepth = krakenDepthReturn.getResult().get(krakenCurrencyPair);
//		List<LimitOrder> bids = KrakenAdapters.adaptOrders(krakenDepth.getBids(), currency, tradableIdentifier, "bids");
//		List<LimitOrder> asks = KrakenAdapters.adaptOrders(krakenDepth.getAsks(), currency, tradableIdentifier, "asks");
//		Comparator<LimitOrder> dateComparator = new Comparator<LimitOrder>() {
//
//			@Override
//			public int compare(LimitOrder o1, LimitOrder o2) {
//
//				return o1.getTimestamp().compareTo(o2.getTimestamp());
//			}
//		};
//		bids.addAll(asks);
//		Date timeStamp = Collections.max(bids, dateComparator).getTimestamp();
		ArrayList<LimitOrder> list = new ArrayList<LimitOrder>();
		list.add(new LimitOrder(OrderType.ASK, BigDecimal.ONE, "BTC", "EUR", "1", new Date(), BigMoney.zero(CurrencyUnit.EUR)));
		
		ArrayList<LimitOrder> list2 = new ArrayList<LimitOrder>();
		list2.add(new LimitOrder(OrderType.BID, BigDecimal.ONE, "BTC", "EUR", "1", new Date(), BigMoney.zero(CurrencyUnit.EUR)));
		return new OrderBook(new Date(), list, list2);
	}

	@Override
	public Trades getTrades(String tradableIdentifier, String currency, Object... args) throws IOException {
		throw new NotAvailableFromExchangeException();
	}

	@Override
	public ExchangeInfo getExchangeInfo() throws IOException {

		throw new NotAvailableFromExchangeException();
	}

	/**
	 * Verify
	 * 
	 * @param tradableIdentifier
	 *            The tradable identifier (e.g. BTC in BTC/USD)
	 * @param currency
	 *            The transaction currency (e.g. USD in BTC/USD)
	 */
	private void verify(String tradableIdentifier, String currency) {

		Assert.notNull(tradableIdentifier, "tradableIdentifier cannot be null");
		// Assert.notNull(currency, "currency cannot be null");
		// Assert.isTrue(KrakenUtils.isValidCurrencyPair(new
		// CurrencyPair(tradableIdentifier, currency)),
		// "currencyPair is not valid:" + tradableIdentifier + " " + currency);
	}

	

}
