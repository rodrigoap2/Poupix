import React from 'react';
import { View, StyleSheet, Dimensions } from 'react-native';

const height = Dimensions.get('window').height;

const Spacer = ({ children }) => {
    return (
        <View style={styles.spacer}>{children}</View>
    );
}

const styles = StyleSheet.create({
    spacer: {
        marginTop: height * 0.02
    }
});

export default Spacer;