import React, { Component } from 'react';
import InfoItem from '../ui/InfoItem';
import { Card, CardItem, Body, Text } from 'native-base';

import { getGameStats } from "../../store/actions/profileActions";

import { connect } from "react-redux";

import { strings } from '../i18n';
import AppStyles from '../AppStyles';

class GameStats extends Component {
	render() {
		return (
			<Card style={{flex:1, marginLeft: 15, marginRight: 15, backgroundColor: 'rgba(52, 52, 52, 0)'}}>
				<CardItem style={{ backgroundColor: AppStyles.primaryColor, justifyContent: 'center',
                    		paddingTop:5, paddingBottom:5}}>
					<Text style={{ fontWeight: 'bold', fontSize: 20, color: 'white' }}>{strings("profile.stats")}</Text>
				</CardItem>
				<CardItem style={{flex:1,paddingTop:5, backgroundColor: AppStyles.panelBackground, flexDirection: 'column'}}>
					<InfoItem labelValue={strings('profile.gamePoints')} itemValue={' = ' + this.props.gameStats.totalPoints} />
					<InfoItem labelValue={strings('profile.commonCards')} itemValue={' = ' + this.props.gameStats.totalCommonCardsOpened} />
					<InfoItem labelValue={strings('profile.rareCards')} itemValue={' = ' + this.props.gameStats.totalRareCardsOpened} />
					<InfoItem labelValue={strings('profile.uniqueCards')} itemValue={' = ' + this.props.gameStats.totalUniqueCardsOpened} />
				</CardItem>
			</Card>
		);
	}

	componentDidMount() {
		this.props.getGameStats();
	}
}
const mapStateToProps = state => {
	return {
		gameStats: state.profile.gameStats
	};
};


const mapDispatchToProps = dispatch => {
	return {
		getGameStats: () => dispatch(getGameStats()),
	};
};

export default connect(mapStateToProps, mapDispatchToProps)(GameStats);