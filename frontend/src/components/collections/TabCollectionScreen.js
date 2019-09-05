import React, { Component } from 'react';
import { Container } from 'native-base';
import { ImageBackground } from "react-native";

import AvailableCategories from './AvailableCategories';
import FavoriteCollections from './FavoriteCollections';
import texture from "../../assets/images/texture2.jpg";
import AchievementPopup from '../profile/AchievementPopup';
import { setAchievementPopupVisible } from "../../store/actions/profileActions";

import { connect } from "react-redux";

class TabCollectionScreen extends Component {
	render() {
		var achievementId = 0;
		if(this.props.accountAchievement != null && this.props.accountAchievement.achievement != null){
			achievementId = this.props.accountAchievement.achievement.id;
		}
		return (
			<Container>
				<ImageBackground source={texture} style={{ width: "100%", flex: 1 }}>
					<FavoriteCollections />
					<AvailableCategories />
					<AchievementPopup achievementId={achievementId} setVisible={this.setModalVisible.bind(this)} visible={this.props.openAchievementPopup} />
				</ImageBackground>
			</Container>
		);
	}

	setModalVisible(visible) {
		this.props.setAchievementPopupVisible(visible);
	}
}

const mapStateToProps = state => {
	return {
		accountAchievement: state.profile.accountAchievement,
		openAchievementPopup: state.profile.openAchievementPopup
	};
};

const mapDispatchToProps = dispatch => {
	return {
		setAchievementPopupVisible: (isVisible) => dispatch(setAchievementPopupVisible(isVisible))
	};
};

export default connect(mapStateToProps, mapDispatchToProps)(TabCollectionScreen);