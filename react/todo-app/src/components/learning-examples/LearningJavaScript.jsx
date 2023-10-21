const person = {
    name: 'Arjun Bhalekar',
    address: {
        line1: '1, Shrawani Building, Shivalaya HSG. SOC.',
        city: 'Pune',
        country: 'IN'
    },
    profiles : ['twitter', 'linkedIn', 'instagram', 'FaceBook'],
    printProfile : () => {
        person.profiles.map(
            profile => console.log(profile)
        )
    }
}

export default function LearningJavaScript() {

    return (
        <div className="LearningJavaScript">
            <div>{person.name}</div>
            <div>
                {person.address.line1}, {person.address.city}, {person.address.country}
            </div>
            <div>{person.profiles[0]}, {person.profiles[1]}</div>
            <div>{person.printProfile()}</div>
        </div>
    );

}