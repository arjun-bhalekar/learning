import { PropTypes } from 'prop-types'

export default function CounterButton({ by, incrementMethod, decrementMethod }) {

    return (
        <div className="Counter">
            {/* <span className="count">{count}</span><br /> */}
            <button className="counterButton"
                // style={buttonStyle}
                onClick={ () => incrementMethod(by) }
            >+{by}</button>
            <button className="counterButton"
                onClick={() => decrementMethod(by) }
            >-{by}</button>
        </div>
    )
}

CounterButton.propTypes = {
    by: PropTypes.number
}

CounterButton.defaultProps = {
    by: 1
}