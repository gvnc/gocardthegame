import React, { Component } from 'react';
import { Card, CardItem, Body, Text, Left } from 'native-base';
import { ScrollView, ActivityIndicator, StyleSheet } from 'react-native';

import { Image } from "react-native-expo-image-cache";
import { connect } from "react-redux";
import { getAllAchievements, isAchievementDone } from "../../store/actions/profileActions";

import apiConfig from '../../store/actions/apiConfig';
import AchievementPopup from './AchievementPopup';

import { strings } from '../i18n';
import AppStyles from '../AppStyles';

class Achievements extends Component {

	state = {
		modalVisible: false,
		selectedAchievementId: 0
	};

	render() {

		let thisClass = this;

		let allAchievements = null;

		if (this.props.allAchievementsList != null && this.props.allAchievementsList.length > 0) {

			allAchievements = this.props.allAchievementsList.map(function (achievement, index) {

				let cardIndex = "achievementCard" + index;
				return (
					<Card style={{ backgroundColor: AppStyles.panelBackground, width: 240, height: 120, marginRight:5 }} key={cardIndex}>
						<CardItem style={{ backgroundColor: AppStyles.panelBackground }} 
									button onPress={() => thisClass.openAchievementDetail(achievement.id)} >
							<Left>
								<Image uri = {apiConfig.url + '/images/' + achievement.capImageUrl} style={styles.image} />
								<Body style={{height:96}}>
									<Text style={{fontSize: 15}}>{strings(achievement.title)}</Text>
									<Text note style={{fontSize: 13}}>{strings(achievement.description)}</Text>
								</Body>
							</Left>
						</CardItem>
					</Card>
				);
			});
		}

		return (
			<Card transparent style={{marginLeft:15, marginRight:15, backgroundColor: AppStyles.primaryColor}}>
				<CardItem style={{ backgroundColor: AppStyles.primaryColor, paddingTop:5, paddingBottom:0,
						 justifyContent:'center' }}>
					<Text style={{ fontWeight: 'bold', fontSize: 20, color: 'white' }}>{strings("profile.achievements")}</Text>
				</CardItem>
				<CardItem style={{ backgroundColor: AppStyles.primaryColor, paddingTop:0, paddingBottom:4, paddingRight: 8, paddingLeft: 8}}>
					<ScrollView style={{ width: '100%', height: 130}} horizontal={true}>
						{allAchievements}
						<ActivityIndicator animating={this.props.isLoading && this.props.allAchievementsList.length == 0} size="large" color={AppStyles.primaryColor} />
						<AchievementPopup achievementId={this.state.selectedAchievementId} setVisible={this.setModalVisible.bind(this)} visible={this.state.modalVisible} />
					</ScrollView>
				</CardItem>
			</Card>
		);
	}

	setModalVisible(visible) {
		this.setState({ modalVisible: visible });
	}

	openAchievementDetail(achievementId) {

		this.props.isAchievementDone(achievementId);

		this.setState(
			{
				selectedAchievementId: achievementId,
				modalVisible: true
			}
		);
	}

	componentDidMount() {
		//this.props.getAllAchievements();
	};
}

const styles = StyleSheet.create({
	image: {
		width: 80,
		height: 96
	}
});

const mapStateToProps = state => {
	return {
		allAchievementsList: state.profile.allAchievements,
		isLoading: state.uiLoading.allAchievements
	};
};

const mapDispatchToProps = dispatch => {
	return {
		getAllAchievements: () => dispatch(getAllAchievements()),
		isAchievementDone: (achievementId) => dispatch(isAchievementDone(achievementId))
	};
};

export default connect(mapStateToProps, mapDispatchToProps)(Achievements);