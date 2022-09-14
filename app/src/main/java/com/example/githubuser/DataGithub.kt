package com.example.githubuser

object DataGithub {
    
    private val userID = arrayOf(
        "tinywolf709",
        "crazybear293",
        "beautifulfish360",
        "redlion798",
        "silverswan291",
        "bluepeacock968",
        "redbird312",
        "blackfrog555",
        "orangebutterfly881",
        "smallduck162"
    )
    
    private val userName = arrayOf(
        "Alison Reid",
        "Allie Willis",
        "Andi Adams",
        "Florence Fowler",
        "Ricky Robertson",
        "Barb Reed",
        "Derrick Long",
        "Flenn Flores",
        "Brendan Doyle",
        "Brad Doyle"
    )
    
    private val company = arrayOf(
        "NIMON",
        "LUXURIA",
        "QUADEEBO",
        "GRONK",
        "ULTRIMAX",
        "MEGAMAX",
        "GOOGON",
        "ALIBATUK",
        "AMAZAPA",
        "MICROHARD"
    )
    
    private val email = arrayOf(
        "alisonreid@nimon.com",
        "alliewillis@luxuria.com",
        "andiadams@quadeebo.com",
        "florencefowler@gronk.com",
        "rickyrobertson@ultrimax.com",
        "barbreed@megamax.com",
        "derricklong@googon.com",
        "flennflores@alibatuk.com",
        "brendandoyle@amazapa.com",
        "braddoyle@microhard.com"
    )
    
    private val location = arrayOf(
        "Newbridge",
        "LeixLip",
        "Westport",
        "Arklow",
        "Killarney",
        "Passage West",
        "Cavan",
        "Tipperary",
        "Greystones",
        "Cashel"
    )
    
    private val photoUrl = arrayOf(
        "https://randomuser.me/api/portraits/women/60.jpg",
        "https://randomuser.me/api/portraits/women/19.jpg",
        "https://randomuser.me/api/portraits/men/75.jpg",
        "https://randomuser.me/api/portraits/women/33.jpg",
        "https://randomuser.me/api/portraits/men/76.jpg",
        "https://randomuser.me/api/portraits/women/84.jpg",
        "https://randomuser.me/api/portraits/men/13.jpg",
        "https://randomuser.me/api/portraits/men/26.jpg",
        "https://randomuser.me/api/portraits/men/83.jpg",
        "https://randomuser.me/api/portraits/men/10.jpg"
    )
    
    private val follower = arrayOf(
        130, 1123, 573, 709, 122, 2709, 2233, 2093, 1123, 13090
    )
    
    private val follwing = arrayOf(
        23, 311, 323, 992, 33, 2333, 325, 907, 890, 7392
    )
    
    private val repositories = arrayOf(
        1, 31, 9, 53, 3, 170, 15, 62, 25, 227
    )
    
    val listData: ArrayList<gitHubUser> get() {
        val list = arrayListOf<gitHubUser>()
        for (posisi in userName.indices)
        {
            var varUser = gitHubUser()
            varUser.userID = userID[posisi]
            varUser.username = userName[posisi]
            varUser.email = email[posisi]
            varUser.company = company[posisi]
            varUser.location = location[posisi]
            varUser.photo   = photoUrl[posisi]
            varUser.follower = follower[posisi]
            varUser.follwing = follwing[posisi]
            varUser.repository = repositories[posisi]
            list.add(varUser)
        }
        return  list
    }
}