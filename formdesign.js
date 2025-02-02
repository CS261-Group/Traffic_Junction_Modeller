
$(() => {
    // when the number of lanes changes, we must truncate or generate the other lanes
    // dynamically
    for (const direction of ["n", "e", "s", "w"]) {
        const dropdownIn = `#${direction}numlanesin`;
        const listIn = `#${direction}inlanes`;
        const listDir = `#${direction}dirlanes`;
        const listOut = `#${direction}outlanes`;
        $(dropdownIn).on("change", () => {
            const numLanes = Number.parseInt($(`${dropdownIn} option:selected`).val());
            console.log(numLanes);
    
            // remove all previous
            $(listIn).empty();
            $(listDir).empty();
            $(listOut).empty();
    
            // add new lanes
            for (let i = 0; i < numLanes; i++) {
                $(listIn).append(`<div class"inlaneconf"> <li> Lane #${i + 1}</li>
                    <input type="number" value="100">
                </div>`);
            }

            // add new lanes
            for (let i = 0; i < numLanes; i++) {
                $(listDir).append(`<div class"dirlaneconf"> <li> Lane #${i + 1}
                        <div>
                            <input type="checkbox" value="east" />
                            <label for="east"><em>Eastbound</em> &#8594;</label>
                        </div>
                        <div>
                            <input type="checkbox" value="north" />
                            <label for="north"><em>Northbound</em>&#8593;</label>
                        </div>
                        <div>
                            <input type="checkbox" value="west" />
                            <label for="west"><em>Westbound</em> &#8592;</label>
                        </div>
                        </li>
                </div>`);
            }

            // add new lanes
            for (let i = 0; i < numLanes; i++) {
                $(listOut).append(`<div class"outlaneconf"> <li> Lane #${i + 1}</li>
                    <input type="number" value="100">
                </div>`);
            }
        });
    }

    // force dropdown onchange event to generate list of lanes
    $("#nnumlanesin").trigger("change");
    $("#enumlanesin").trigger("change");
    $("#snumlanesin").trigger("change");
    $("#wnumlanesin").trigger("change");
    $("#nnumlanesout").trigger("change");
    $("#enumlanesout").trigger("change");
    $("#snumlanesout").trigger("change");
    $("#wnumlanesout").trigger("change");
});